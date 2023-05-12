package com.jobtang.sourcecompany.api.user.service;

import com.jobtang.sourcecompany.api.user.dto.EmailAndCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.Random;

import static org.springframework.security.core.context.SecurityContextHolder.setContext;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    //의존성 주입을 통해서 필요한 객체를 가져온다.
    private final JavaMailSender emailSender;
    private final RedisTemplate<String, String> redisTemplate;

    //랜덤 인증 코드 생성
    public String createCode() {
        String authNum ="";
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for(int i=0;i<8;i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0 :
                    key.append((char) ((int)random.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) ((int)random.nextInt(26) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(9));
                    break;
            }
        }
        authNum = key.toString();
        return authNum;
    }

    // 메일 양식 작성해서 전송
    // 코드 전송후 레디스에 5분 유효기간으로 저장
    public void sendEmailCert(String email){

        String authNum = createCode(); //인증 코드 생성
        String setFrom = "kimjinho.dev@gmail.com"; //email-config에 설정한 자신의 이메일 주소(보내는 사람)
        String toEmail = email; //받는 사람
        String title = "Source-Company 회원가입 인증 번호"; //제목

        MimeMessage message = emailSender.createMimeMessage();
        try {
            message.addRecipients(MimeMessage.RecipientType.TO, toEmail); //보낼 이메일 설정
            message.setSubject(title); //제목 설정
            message.setFrom(setFrom); //보내는 이메일
        } catch (MessagingException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 안녕하세요 소스컴퍼니입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 복사해 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= authNum+"</strong><div><br/> ";
        msgg+= "</div>";

        try {
            message.setText(msgg, "utf-8", "html");//내용
            emailSender.send(message);
        } catch (MessagingException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(60 * 5L);
        valueOperations.set(toEmail.replace("\"",""), authNum, expireDuration);
    }

    // 이메일 체크
    // 이메일을 레디스에서 키값으로 조회후, 받은 코드값과 비교해서 확인처리
    public boolean checkEmailCert(EmailAndCode emailAndCode) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String setCode = valueOperations.get(emailAndCode.getEmail());
        if (setCode.equals(emailAndCode.getCode())) {
            return true;
        }
        return false;
    }
}

