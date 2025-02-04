# Mobile Programming Mid-term Project
소프트웨어학부 20181299 박희주

### 과제 설명

1. 평가 : 첫번째 화면 5점, 두번째 화면 5점, 세번째 화면 5점 (만점 15점, 각 화면의 구성+동작으로 평가)

2. 총점  : 15점
   * 가산점 : Firebase 연동
   * 감점  : 동작중 비정상적인 앱 종료

첫번째 화면 (Relative Layout 혹은 Fragment 사용)
- 상품 선택 페이지
- 상품은 2개이상 화면에 출력. 각 상품의 제품명, 가격 정보 표시
- 상품 선택하면 아래에 버튼으로 구매 혹은 장바구니 선택
- 장바구니 버튼을 클릭시에는 두번째 화면으로 이동
- 구매 버튼을 클릭시에는 세번째 화면으로 이동

두번째 화면 (Linear Layout 혹은 Fragment 사용)
- 장바구니 페이지
- 장바구니에 추가한 상품명, 가격 정보 출력
- 구매 버튼과 홈버튼을 출럭
- 홈버튼을 클릭하면 첫번째 페이지로 이동
- 상품별로 선택(Radio 버튼, 체크 박스 활용) 여부 체크후에
  구매 버튼을 클릭하면 세번째 페이지로 이동

세번째 화면 (Table Layout, Grid Layout 중 하나 사용)
- 구매 페이지
- 선택한 제품명, 가격 정보를 출력
- 선택한 제품이 여러개면 결재할 총합 선택
- 주소정보, 연락처 입력
- 구매 완료시에 첫번째 화면으로 이동

* 각페이지 구성시에 View을 상속한 여러가지 위젯을 사용하여 화면을 구성
  (기능에 맞는 위젯 선택하여 구성)
   ListView, GridView, AdapterView, ToolBar, Text View, CheckBox, Switch, ToggleButton, RadioButton, ImageView, ImageButton 등

---

### 개발 환경
- Android Studio
- MacOS 10.15 Catalina
- Firebase Realtime Database
- Java
- Glide
- Target SDK 30, min SDK 23

* Firebase 연동이 되어있어 소스코드를 직접 빌드하면 key 누락으로 정상 실행되지 않습니다. apk폴더에 테스트를 위한 릴리즈 apk를 업로드했으니, 채점에 사용하시면 됩니다!

---

### 프로젝트 소개

##### 1. 첫번째 화면 / 상품 선택 페이지

- 앱에 접속하면 가장 먼저 보이는 화면, Firebase DB 정보를 리사이클러뷰에 담아서 보여줌  
  <img width="250" src="https://github.com/parkhuijoo/market-app-android/blob/main/imgs/1.png">

- 원하는 상품을 선택할 경우 하단에 장바구니, 구매 버튼이 노출  
  <img width="250" src="https://github.com/parkhuijoo/market-app-android/blob/main/imgs/2.png">

- ADD TO CART 클릭 시 장바구니에 담기고 장바구니로 이동 또는 우측 상단 장바구니 버튼 클릭 시 장바구니로 이동  
  <img width="250" src="https://github.com/parkhuijoo/market-app-android/blob/main/imgs/3.png">

- BUY NOW 클릭 시 선택한 상품을 바로 구매하는 페이지로 이동  
  <img width="250" src="https://github.com/parkhuijoo/market-app-android/blob/main/imgs/4.png">

##### 2. 두번째 화면 / 장바구니 페이지

- 장바구니에 담긴 아이템이 리사이클러뷰에 담겨 보여짐. 같은 상품을 여러개 구매하고 싶을 수 있기에 장바구니에는 상품이 중복해서 담길 수 있음
- 아이템마다 체크박스가 있어 구매할 상품 OR 삭제할 상품을 선택할 수 있음
- 체크한 상품에 대한 구매/삭제는 하단 버튼으로 조작(선택한 상품이 없을 경우에는 아무 일도 일어나지 않음)
- 좌측 상단 버튼으로 홈으로 돌아갈 수 있으며, 뒤로가기 버튼은 의도적으로 비활성화 하였음  
  <img width="250" src="https://github.com/parkhuijoo/market-app-android/blob/main/imgs/5.png">

##### 3. 세번째 화면 / 구매 페이지

- 구매할 상품을 넘겨받아 상품 정보를 보여주고, 상품 정보가 여러개인 경우 외 X건으로 총 갯수를 출력. 총 합산 금액을 출력  
  <img width="250" src="https://github.com/parkhuijoo/market-app-android/blob/main/imgs/6.png">

- 주소와 연락처를 입력해야만 구매가 정상적으로 진행됨(예외처리)  
  <img width="250" src="https://github.com/parkhuijoo/market-app-android/blob/main/imgs/7.png">

- 구매 완료시 토스트 띄워주고 첫번째 화면으로 이동
  <div>
    <img width="250" src="https://github.com/parkhuijoo/market-app-android/blob/main/imgs/8.png">
    <img width="250" src="https://github.com/parkhuijoo/market-app-android/blob/main/imgs/9.png">
  </div>

---

### 추가 구현

> Firebase Realtime Database를 이용하여 실시간으로 상품 정보를 추가/삭제/변경, 앱에 반영할 수 있음
> Database의 구조는 다음과 같음
<img width="500" src="https://github.com/parkhuijoo/market-app-android/blob/main/imgs/10.png">

> 리사이클러뷰의 디자인을 직접 고안하여 구현하였고, 첫번째 화면에서 상품을 클릭했을 때, 하단의 버튼이 나타나도록 구현
> 클릭한 상품을 다시 클릭할 경우 버튼이 사라져 상품을 살펴볼 때 더욱 편리하도록 구성

