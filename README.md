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

<img src="https://img.shields.io/badge/java-3a75b0?style=for-the-badge&logo=java&logoColor=black"> 
<img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
<img src="https://img.shields.io/badge/JPA Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white">
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<img src="https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=JUnit5&logoColor=white">
<img src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens">
<img src="https://img.shields.io/badge/Amazon%20S3-569A31?style=for-the-badge&logo=Amazon%20S3&logoColor=white">
<img src="https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white">
<img src="https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white">
<img src="https://img.shields.io/badge/QUERYDSL-4F84C6?style=for-the-badge&logo=data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDABMNDxEPDBMREBEWFRMXHTAfHRsbHTsqLSMwRj5KSUU+RENNV29eTVJpU0NEYYRiaXN3fX59S12Jkoh5kW96fXj/2wBDARUWFh0ZHTkfHzl4UERQeHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHj/wAARCAAXABkDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwCxZ2ct2XEW35Bk5OKnY6arJhZWGw7gD/F2/rTbbyjYzBFlNyOcpnG339qr20Inl2GRI+CdznArt3bucu2wTRBFVkbeh/ixjnuKiq49hdR2zyNF8nXIIyPeqdVF3E1Ykimkh3eW5XcMNjuKdI8DsWWJkyeFDcAY/wAaKKLCuXX1h2sjB5Q3FdpfPb6VmUUUKKjsNtvc/9k=">
<img src="https://img.shields.io/badge/JASYPT-000000?style=for-the-badge">
<img src="https://img.shields.io/badge/RESTASSURED-22b14c?style=for-the-badge&logo=data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAA8KCw0LCQ8NDA0REA8RFiUYFhQUFi0gIhslNS84NzQvNDM7QlVIOz9QPzM0SmRLUFdaX2BfOUdob2dcblVdX1v/2wBDARARERYTFisYGCtbPTQ9W1tbW1tbW1tbW1tbW1tbW1tbW1tbW1tbW1tbW1tbW1tbW1tbW1tbW1tbW1tbW1tbW1v/wAARCAAYABkDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwC7pWm/bnZpH8uFOp7k+gq69jbxQu32WOX5cpGruJOvUg44HsKZp8kENlbtK2VcvHs25BYkcn2GFq2qGN1lvDE18dyoQ2N57DPTPbP4denOkrHmQhHlWhlX+mtBEJ41YR4G5WIJQ+nv9fwrPrpHjG1p7hGWae3dHjB+WLauenb+HjtXN1ElYwrQUXoXdNvvskoEi7oSckAcg+o9DWpDdRbfNkuba4cPmMSDZ5Q9uM/h0oopxk9h0qjWhBqmqxPa/ZrUFi3+slPJb2zgE9ByfSsWiipbbInNzd2f/9k=
">
<img src="https://img.shields.io/badge/python-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54">
<img src="https://img.shields.io/badge/flask-%23000.svg?style=for-the-badge&logo=flask&logoColor=white">
<img src="https://img.shields.io/badge/opencv-%23white.svg?style=for-the-badge&logo=opencv&logoColor=white">
<img src="https://img.shields.io/badge/mediapipe-0098a6?style=for-the-badge&logo=data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDACEXGR0ZFSEdGx0lIyEoMlM2Mi4uMmZJTTxTeWp/fXdqdHKFlr+ihY21kHJ0puOotcbM1tjWgaDr/OnQ+r/S1s7/2wBDASMlJTIsMmI2NmLOiXSJzs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7/wAARCAAXABkDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwCxFDG2nSSFfnB4NS3EMMM0GISwIOVXkmoI1mOnyFXAjzyO9X2IN3b4IPysa0ZzRSa27FSOGJ7W5k8vBBO3PUVQrX/5YXn+838qyKqJnUVrFuO5RbCSE53k8U6Oa2iuInjDABcN9aKKLE87HfbI/KuVw2ZCSv41QooppWFKTluf/9k=">

**Language |** Java 17

**Framework |** Spring Boot 3.2.2

**Data(RDBMS) |** Spring Data JPA

**Build Tool |** Gradle 8.5.0

**Library |** MediaPipe

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
  <img src="image/ERD_D210_FINAL.png" width="70%"/>
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
