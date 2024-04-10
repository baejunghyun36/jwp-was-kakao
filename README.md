# 웹 애플리케이션 서버

## 진행 방법

* 웹 애플리케이션 서버 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정

* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

* [X] Uri 객체를 만든다.
    * [X] Method : String 타입

      ** CASE-SENSITIVE

    * [X] Path : String 타입
    * [X] Params : Map<String, String> 타입
* [X] HttpVersion을 만든다.
    * [X] Scheme : String 타입

      ** CASE-SENSITIVE

    * [X] Major : int 타입
    * [X] Minor : int 타입
    * [X] Raw : String 타입
* [X] 일급 콜렉션을 감싼 HttpHeaders 객체를 만든다.
* [X] Method 객체를 만든다.
* [X] RequestLine 객체를 만든다.
* [X] RequestBody 객체를 만든다.
* [X] RequestLine과 HttpHeaders와 RequestBody를 가진 HttpRequest를 만든다.
* [X] RequestHandler에서 서빙할 파일을 찾아 바이트 배열을 HttpResponse로 넘긴다.
* [X] Redirect 및 Controller 구현한다.
* [X] Content-Type 필터링을 구현한다.
* [X] StatusLine 객체를 만든다.
* [TODO] ResponseBody 객체를 만든다.
* [X] StatusLine과 HttpHeaders와 ResponseBody를 가진 HttpResponse를 만든다.
