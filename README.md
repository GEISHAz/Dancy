# Dancy - 춤 연습 보조 SNS 서비스

<div align="center">
  <br />
  <img src="image/DancyLogo.png" width="70%"/>
  <br />
</div>

## 목차
- [서비스 개요](#서비스-개요)
- [팀원소개](#팀원소개)
- [기술스택](#기술스택)
- [시스템 아키텍처](#시스템-아키텍처)
- [기능소개](#기능소개)
- [프로젝트 산출물](#프로젝트-산출물)



## 서비스 개요
```
'누구나 어디서든'
춤 연습을 할 수 있는 서비스
Dancy로 어서와 ~~ 💃🕺
```


## 팀원소개
<div align="middle">
<table>
    <tr>
        <td height="140px" align="center"> <a href="https://github.com/whalesBob">
            <img src="image/whalesbob.png" width="140px" /> <br><br> 👑 남동우 <br>(Back-End) </a> <br></td>
        <td height="140px" align="center"> <a href="https://github.com/GEISHAz">
            <img src="image/mino.png" width="110px" /> <br><br> 🎮 정민호 <br>(Back-End) </a> <br></td>
        <td height="140px" align="center"> <a href="https://github.com/cnh12">
            <img src="image/cnh.png" width="120px" /> <br><br> 🐰 조남현 <br>(Back-End) </a> <br></td>
        <td height="140px" align="center"> <a href="https://github.com/kangjungsuu">
            <img src="image/bbbbqz.png" width="120px" /> <br><br> ⚽ 강정수 <br>(Front-End) </a> <br></td>
        <td height="140px" align="center"> <a href="https://github.com/sunoftwilight">
            <img src="image/sun.png" width="140px" /> <br><br> 💎 이해진 <br>(Front-End) </a> <br></td>
        <td height="140px" align="center"> <a href="https://github.com/seolyeonpark">
            <img src="image/sy.png" width="110px" /> <br><br> 🥨 박설연 <br>(Front-End) </a> <br></td>
    </tr>
</table>
</div>


## 기술스택

### 프론트엔드

<div align="middle">

<img src="https://img.shields.io/badge/JavaScript-FFE249?style=for-the-badge&logo=javascript&logoColor=white">
<img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=white">
<img src="https://img.shields.io/badge/axios-5A29E4?style=for-the-badge&logo=axios&logoColor=white">
<img src="https://img.shields.io/badge/styledcomponents-DB7093?style=for-the-badge&logo=styledcomponents&logoColor=white">
<img src="https://img.shields.io/badge/TailWind-19B4B9?style=for-the-badge&logo=tailwindcss&logoColor=white">
<img src="https://img.shields.io/badge/vite-646CFF?style=for-the-badge&logo=vite&logoColor=white">
<img src="https://img.shields.io/badge/recoil-000000?style=for-the-badge&logo=recoil&logoColor=white">
<img src="https://img.shields.io/badge/yarn-3B9DC8?style=for-the-badge&logo=yarn&logoColor=white">

**Language |** Javascript(Node: 18.19.0)

**Framework |** React 18.2.0

**Library |** Axios 1.6.7, Styled Components 6.1.8, Recoil 0.7.7, Yarn 1.22.21, EventSourcePolyfill 1.0.31

<br>
<br>

</div>


### 백엔드

<div align="middle">

<img src="https://img.shields.io/badge/java-3a75b0?style=for-the-badge&logo=java&logoColor=black"> <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/spring mvc-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/JPA Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white">
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

**Language |** Java 17

**Framework |** Spring Boot 3.2.2

**Data(RDBMS) |** Spring Data JPA

**Build Tool |** Gradle 8.5.0

</div>

<br>
<br>

### 인프라

<div align="middle">

<img src="https://img.shields.io/badge/gitlab-F05032?style=for-the-badge&logo=gitlab&logoColor=white">
<img src="https://img.shields.io/badge/AWS EC2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white">
<img src="https://img.shields.io/badge/maria DB-4479A1?style=for-the-badge&logo=mariadb&logoColor=white">
<img src="https://img.shields.io/badge/jenkins-111111?style=for-the-badge&logo=jenkins&logoColor=white">
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
<img src="https://img.shields.io/badge/nginx-00953B?style=for-the-badge&logo=nginx&logoColor=white">


**DB |** MariaDB 10.11 LTS

**Server |** GitLab, Jenkins, Docker, Nginx

</div>

<br>
<br>

## 시스템 아키텍처
<div align="middle">
  <br />
  <img src="image/시스템아키텍처.png" width="70%"/>
  <br />
</div>

<br>
<br>

## 기능소개

🧡 메인 페이지

<img src = "image/메인페이지.gif" width="600">

<br>
<br>

💛 영상 변환 페이지

<img src = "image/CREATE.gif" width="600">

- 안무 영상과 나의 영상을 첨부하고 변환 버튼을 클릭하면 상단 헤더에 로딩이 시작됩니다.
- 안무 영상은 서비스에서 제공하는 가이드라인 영상을 선택하거나, 본인이 원하는 안무 영상을 첨부할 수 있습니다.

<br>
<br>

💚 PRACTICE 페이지

<img src = "image/PRACTICE.gif" width="600">

- 구간 반복을 통해서 정확도가 떨어지는 안무를 연습할 수 있습니다.
- 연속 재생을 통해서 전체 영상을 반복해서 연습할 수 있습니다.
- 열심히 연습한 안무영상을 게시할 수 있습니다.
<br>
<br>


💙 STAGE 페이지

<img src = "image/STAGE (FEED).gif" width="600">

- 유저들이 업로드한 영상들을 한눈에 볼 수 있습니다.
<br>
<br>

🤎 영상 상세 페이지

<img src = "image/DETAIL.gif" width="600">

- STAGE 페이지에서 영상을 클릭하면 이동하는 페이지입니다.
- 원하는 영상을 보관하고 좋아요를 누를 수 있습니다.
- 유저들과 댓글을 통해 소통할 수 있습니다.
<br>
<br>

💜 PROFILE 페이지

<img src = "image/PROFILE.gif" width="600">

- 유저가 업로드한 영상과 보관한 영상을 조회할 수 있습니다.
- 다른 유저들을 팔로우 할 수 있습니다.
<br>
<br>


🖤 검색 결과 페이지

<img src = "image/SEARCH.gif" width="600">

- 제목과 닉네임을 기준으로 검색한 영상들을 조회할 수 있습니다.
<br>
<br>

## 프로젝트 산출물

- API 명세서

> https://www.notion.so/API-a481909030e84aedb5ef9d27b407e68a?pvs=4

<br>
<br>

- ERD
<div align="middle">
  <br />
  <img src="image/erd.png" width="70%"/>
  <br />
</div>
<br>
<br>

- 와이어프레임

> https://www.figma.com/file/H3b5XPTkYYCEY0vn283EsU/%EC%B5%9C%EA%B0%95D210%ED%94%BC%EA%B7%B8%EB%A7%88-(Copy)?type=design&node-id=2%3A2&mode=design&t=4V5T39w1D0HACvVE-1

<br>
<br>

- 컨벤션

![브랜치컨벤션](/uploads/c176a292849dbc779294b10433c820de/스크린샷_2024-02-16_오전_3.09.23.png){width="600"}
![커밋컨벤션](/uploads/d79bde29c8741fa3aa4813125ffd05ba/스크린샷_2024-02-16_오전_3.09.48.png){width="600"}
<br>
<br>
- 포팅매뉴얼

>[포팅매뉴얼](./exec/D210.docx)
