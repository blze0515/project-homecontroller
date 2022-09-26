import "../../css/userupdate.css";
import React, { useRef, useState, useCallback, useEffect } from "react";
import { API_BASE_URL } from "../../app-config";
import { Link } from "@mui/material";
import axios from "axios";
import "../../css/mypagesidebar.css";

const ariaLabel = { "aria-label": "description" };
function MyPoint() {
  // 화면 input 항목 = DB 정보
  const [inputs, setInputs] = useState({
    userZip: "",
    userAddr: "",
    userAddrDetail: "",
    userId: "",
    userMail: "",
    userName: "",
    userNickname: "",
    userPoint: "",
    userTel: "",
    userPw: "",
  });

  // db에서 회원정보 데이터 받아오기
  const getUser = () => {
    let url = "http://localhost:8080/api/user/getUser";
    var userInfoStr = sessionStorage.getItem("USER_INFO"); // 로그인한 사용자의 ID 를 가져오기 위한 세션 정보 활용
    var userInfo = JSON.parse(userInfoStr); // 세션에 JSON String 으로 등록된 사용자 정보를 JSON 형태로 변환
    axios({
      method: "post",
      url: url,
      data: { userId: userInfo.userId }, // DB 조회를 위한 사용자 ID
    }).then((response) => {
      //    }).then(({data}) => { // ES6 구조분해 문법
      //      var usrData = data.data[0]; // ES6 구조분해 문법
      var usrData = response.data.data[0]; // DB 조회 결과
      // DB 조회 결과를 화면 input 항목에 반영
      setInputs({
        userZip: usrData.userZip,
        userAddr: usrData.userAddr,
        userAddrDetail: usrData.userAddrDetail,
        userId: usrData.userId,
        userMail: usrData.userMail,
        userName: usrData.userName,
        userNickname: usrData.userNickname,
        userPoint: usrData.userPoint,
        userTel: usrData.userTel,
        userPw: usrData.userPw,
      });
      console.log(usrData);
    });
  };

  React.useEffect(() => {
    getUser();
  }, []);

  const [toggleState, setToggleState] = useState(1);

  const toggleTab = (index) => {
    setToggleState(index);
  };

  // 화면 input 항목들의 변경 내용을 React 상태 갱신
  const onChange = (e) => {
    const { name, value } = e.target;
    setInputs({
      ...inputs,
      [name]: value,
    });
  };

  //회원탈퇴 버튼 클릭시
  const deleteUserInfo = (e) => {
    if (window.confirm("정말로 떠나시겠어요?😢")) {
      console.log("탈퇴버튼 클릭");
      let url = "http://localhost:8080/api/mypage/deleteUserInfo";
      axios({
        method: "post",
        url: url,
        headers: {
          Authorization: "Bearer " + sessionStorage.getItem("ACCESS_TOKEN"),
        },
        data: inputs, // 화면 input 항목에 대한 정보
      }).then((response) => {
        alert("삭제됨");

        //로컬과 세션에 담긴 유저 정보 삭제
        localStorage.removeItem("USER_INFO");
        localStorage.removeItem("ACCESS_TOKEN");
        sessionStorage.removeItem("USER_INFO");
        sessionStorage.removeItem("ACCESS_TOKEN");
        console.log("잘되나");
        window.location.href = "/Join";
      });
    }
  };

  return (
    <div className="container">
      <body>
        <div className="MyNavIndex">
          <div class="nav_wrapper">
            <nav className="MyNavMenu">
              <ul>
                <li>
                  <Link href="/mypage" title="Link">
                    MYPAGE
                  </Link>
                </li>
                <li>
                  <a href="#Link" title="Link">
                    나의 정보
                  </a>
                  <ul>
                    <li>
                      <a href="/userupdate" title="Link ">
                        나의정보 수정
                      </a>
                    </li>
                    <li>
                      <a href="/outmembers" title="Link">
                        멤버십 해지
                      </a>
                    </li>
                  </ul>
                </li>
                <li>
                  <a href="/wishlist" title="Link">
                    위시리스트
                  </a>
                </li>
                <li>
                  <a href="/cart" title="Link">
                    장바구니
                  </a>
                </li>
                <li>
                  <a href="#Link" title="Link">
                    포인트/쿠폰
                  </a>
                  <ul>
                    <li>
                      <a href="/mypoint" title="Link">
                        포인트
                      </a>
                    </li>
                    <li>
                      <a href="#Link" title="Link">
                        쿠폰
                      </a>
                    </li>
                  </ul>
                </li>

                <li>
                  <a href="/orderlist" title="Link">
                    주문내역
                  </a>
                  {/* <ul>
                <li>
                  <a href="/orderlist" title="Link">
                    주문
                  </a>
                </li>
                <li>
                  <a href="#Link" title="Link">
                    반품
                  </a>
                </li>
                <li>
                  <a href="#Link" title="Link">
                    교환
                  </a>
                </li>
              </ul> */}
                </li>

                <li>
                  <a href="#Link" title="Link">
                    나의 게시글
                  </a>
                  <ul>
                    <li>
                      <a href="/myinquiry" title="Link">
                        자유게시판
                      </a>
                    </li>
                    <li>
                      <a href="/myreviewlist" title="Link">
                        상품후기
                      </a>
                    </li>
                  </ul>
                </li>
              </ul>
            </nav>
          </div>
        </div>
      </body>

      <div style={{ margin: "0 auto" }}>
        <img src="https://i.pinimg.com/564x/ec/c5/22/ecc5225d244576eee4bbd9c71e34fafc.jpg" />
      </div>

      <div className="outCard">
        <div class="card text-center">
          <div class="card-body">
            <div className="pointPart">
              <h5 style={{ marginBottom: "50px" }}>
                {inputs.userNickname} 님의 현재까지 누적 포인트 :{" "}
                {inputs.userPoint} POINT
              </h5>
              <h5>지금 바로 사용 가능한 쿠폰</h5>
              <img src="https://i.pinimg.com/564x/84/ab/ab/84abab661b1bab887559f001d80bc1f5.jpg"></img>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
export default MyPoint;
