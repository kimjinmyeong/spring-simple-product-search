package io.github.kimjinmyeong.myselectshop.naver.controller;

import io.github.kimjinmyeong.myselectshop.naver.dto.SignupRequestDto;
import io.github.kimjinmyeong.myselectshop.naver.dto.UserInfoDto;
import io.github.kimjinmyeong.myselectshop.naver.entity.UserRoleEnum;
import io.github.kimjinmyeong.myselectshop.naver.security.UserDetailsImpl;
import io.github.kimjinmyeong.myselectshop.naver.service.FolderService;
import io.github.kimjinmyeong.myselectshop.naver.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    private final FolderService folderService;

    @GetMapping("/users/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/users/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/users/signup")
    public String signup(@Valid SignupRequestDto requestDto, BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(!fieldErrors.isEmpty()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error("{} field : {}", fieldError.getField(), fieldError.getDefaultMessage());
            }
            return "redirect:/api/users/signup";
        }

        userService.signup(requestDto);

        return "redirect:/api/users/login-page";
    }

    @GetMapping("/user-info")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        UserRoleEnum role = userDetails.getUser().getRole();
        boolean isAdmin = (role == UserRoleEnum.ADMIN);

        return new UserInfoDto(username, isAdmin);
    }

    @GetMapping("/user-folder")
    public String getUserInfo(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        model.addAttribute("folders", folderService.getFolders(userDetails.getUser()));

        return "index :: #fragment";
    }

}
