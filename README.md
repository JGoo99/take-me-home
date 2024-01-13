# 👽🛸탱미홈: TAKE ME HOME [상대음감게임]

들려주는 음정을 듣고 우주선을 드래그하여 같은 음정을 찾는 상대음감게임입니다.

[플레이 시연 영상 보러가기👉🏻👉🏻](https://youtu.be/pLfiAwebams?si=JCW6iWWsxNgLg3gX)

---
## 목차
- [플레이 및 컨셉](#플레이-및-컨셉)
- [개요](#개요)
- [개발환경](#개발환경)
- [기능 및 설계](#기능-및-설계)
- [ERD](#erd)

---

## 플레이 및 컨셉

> 음정을 맞춰야만 외계인이 집에 돌아갈 수 있어요!

<br/>

<img src="https://github.com/JGoo99/take-me-home/assets/126454114/eed7caa4-e878-4685-b2b0-d4016d55ebf2" width=60%>

| 위 게임은 게임이 시작되면 랜덤으로 정해진 음을 1초간 들려줍니다.  
| 해당 음을 듣고 우주선 아이콘을 드래그하여 같은 음을 찾습니다.  
| 만약 같은 음정을 찾는 다면 미션 성공!  
| 조금이라도 틀린다면 미션 실패!

---

## 개요

- 개발 인원 : 1인 창작 프로젝트  
- 개발 기간 : 2023.12.20 ~ 2023.12.27 / 2024.01.09 ~ 2024.01.12 (총 12일)  
- 라이브러리 : 오디오 API [Theresa's Sound World](https://theresassoundworld.com/)

---

## 개발환경

<div>
    <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
    <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
    <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=java&logoColor=white">
    <img src="https://img.shields.io/badge/mariadb-003545?style=for-the-badge&logo=java&logoColor=white">
</div>

> Spring Boot 3.1.5 (JDK 17)
>
> Java 17
>
> Gradle - Groovy
>
> JPA
> 
> MariaDB
>
> Spring Security
>
> Lombok
> 
> Validation

---

## 기능 및 설계

### 회원가입 기능

사용자는 회원가입을 할 수 있다. 일반적으로 모든 사용자는 회원가입시 USER 권한 (일반 권한)을 지닌다.  
회원가입시 아이디와 패스워드를 입력받으며, 아이디는 unique 해야한다.

### 로그인 기능

사용자는 로그인을 할 수 있다. 로그인시 회원가입때 사용한 아이디와 패스워드가 일치해야한다.

### 게임 플레이

유저권한이 있는 사용자는 게임 화면에 접근할 수 있다.

### 점수 부여

게임 플레이 중 미션 성공 시 1점을 얻으며 실시간 반영된다.

### 유저 랭킹

모든 유저를 점수를 내림차순으로 페이징 처리하여 정렬해둔 화면을 볼 수 있다.

---

## ERD

<img src="https://github.com/JGoo99/take-me-home/assets/126454114/6127dfd5-df72-4c00-a483-eedfda13ad4a" width="40%">