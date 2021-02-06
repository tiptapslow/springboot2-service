package com.kimsk.springboot.config.auth;

import com.kimsk.springboot.config.auth.dto.OAuthAttributes;
import com.kimsk.springboot.config.auth.dto.SessionUser;
import com.kimsk.springboot.domain.user.User;
import com.kimsk.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
class CustomOauth2UserService implements OAuth2UserService<OAuth2UserRequest,
        OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpsession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate =
                new DefaultOAuth2UserService();

        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //현재 로그인 진행중인 서비스를 구분하는 코드 (구글인지 네이버인지 카카오인지)
        String registrationId =
                userRequest.getClientRegistration().getRegistrationId();

        //로그인 진행시 키가 되는 필드 (primary key)
        String userNameAttributeName =
                userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        //OAuth2UserService 를 통해서 가져온 User 의 attribute 를 담을 클래스
        OAuthAttributes attributes = OAuthAttributes.of(registrationId,
                userNameAttributeName,
                oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        httpsession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user =
                userRepository.findByEmail(attributes.getEmail()).map(e -> e.update(attributes.getName(),
                        attributes.getPicture())).orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
