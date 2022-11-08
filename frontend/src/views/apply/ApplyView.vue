<template>
<div class="wrapper">
    <div class="left_container">
        <SidebarCom :pageName="pageName" :listItem="side"></SidebarCom>
    </div>
    <div class="right_container">
        <PageTitle :title="title"></PageTitle>
        <div class="content_wrap">
            <!-- 상담 및 면담신청 -->
            <div v-if="$route.name === 'consult'" class='consult_container'>
                <form action="">
                    <table class="consult_table">
                        <tr>
                            <td>이름</td>
                            <td>홍길동</td>
                            <td>학번</td>
                            <td>20221234</td>
                        </tr>
                        <tr>
                            <td>연락처</td>
                            <td>
                                <select name="phone1" required>
                                    <option value="010">010</option>
                                    <option value="02">02</option>
                                    <option value="031">031</option>
                                    <option value="032">032</option>
                                    <option value="033">033</option>
                                    <option value="034">034</option>
                                    <option value="035">035</option>
                                    <option value="036">036</option>
                                </select> -
                                <input type='tel' name='phone2' required/> -
                                <input type='tel' name='phone3' required/>
                            </td>
                            <td>이메일</td>
                            <td>
                                <input type="email"
                                        pattern=".+@gmail\.com"
                                        placeholder="example@gmail.com"
                                        required>
                            </td>
                        </tr>
                        <tr>
                            <td>상담분야</td>
                            <td>
                                <select name="" id="">
                                    <option value="교우관계">교우관계</option>
                                </select>
                            </td>
                            <td>신청일자</td>
                            <td>{{ today }}</td>
                        </tr>
                        <tr>
                            <td>상담내용</td>
                            <td colspan="3">
                                <input type="text" size="100" maxlength="100" placeholder="100자 이내로 입력하세요">
                            </td>
                        </tr>
                        <tr>
                            <td>상담일정</td>
                            <td colspan="3">
                                <table class="time_table">
                                    <caption>상담 가능 시간표</caption>
                                    <tr>
                                        <td></td>
                                        <td>월</td>
                                        <td>화</td>
                                        <td>수</td>
                                        <td>목</td>
                                        <td>금</td>
                                    </tr>
                                    <tr>
                                        <td>9~10</td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                    </tr>
                                    <tr>
                                        <td>10~11</td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                    </tr>
                                    <tr>
                                        <td>11~12</td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                    </tr>
                                    <tr>
                                        <td>12~13</td>
                                        <td colspan="5">점심시간</td>
                                    </tr>
                                    <tr>
                                        <td>13~14</td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                    </tr>
                                    <tr>
                                        <td>14~15</td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                    </tr>
                                    <tr>
                                        <td>15~16</td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                    </tr>
                                    <tr>
                                        <td>16~17</td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                    </tr>
                                    <tr>
                                        <td>17~18</td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" style="background-color:#fff">
                                <input type="submit" value="신청하기">
                            </td>
                        </tr>
                    </table>
                    
                </form>
            </div>
            <!-- 스터디룸 예약 -->
            <div v-else-if="$route.name === 'study'" class='study_container'>
                <form action="" class="reserve_box">
                    <div class="title">선택좌석 (좌석도 삽입예정)</div>
                    <div class="seat_status">
                        <input type="button" v-for="(seat, index) in this.seat" :key="seat.no" class="seat" :class="{active: seat.isActive, disable: !seat.status}" @click="myFilter(index)" :disabled="!seat.status" :value=" seat.no">
                    </div>
                    <div class="title">
                        이용시간
                        <p>(하루 최대 이용 시간은 6시간입니다.)</p>
                    </div>
                    <select id="" required>
                        <option value="" disabled selected>시간 선택</option>
                        <option value="9">09:00~11:00</option>
                        <option value="11">11:00~13:00</option>
                        <option value="13">13:00~15:00</option>
                        <option value="15">15:00~17:00</option>
                        <option value="17">17:00~19:00</option>
                        <option value="19">19:00~21:00</option>
                        <option value="21">21:00~23:00</option>
                    </select>
                    <select v-show="timeTable[1]" id="">
                        <option value="" disabled selected>추가 시간 선택</option>
                        <option value="9">09:00~11:00</option>
                        <option value="11">11:00~13:00</option>
                        <option value="13">13:00~15:00</option>
                        <option value="15">15:00~17:00</option>
                        <option value="17">17:00~19:00</option>
                        <option value="19">19:00~21:00</option>
                        <option value="21">21:00~23:00</option>
                    </select>
                    <select v-show="timeTable[2]" id="">
                        <option value="" disabled selected>추가 시간 선택</option>
                        <option value="9">09:00~11:00</option>
                        <option value="11">11:00~13:00</option>
                        <option value="13">13:00~15:00</option>
                        <option value="15">15:00~17:00</option>
                        <option value="17">17:00~19:00</option>
                        <option value="19">19:00~21:00</option>
                        <option value="21">21:00~23:00</option>
                    </select>
                    <input type="button" v-if="timeCount < 2" @click="addTime" value="+">
                    <input type="button" v-if="timeCount >= 1" @click="subTime" value="-">
                    <input type="submit" value="예약하기">
                </form>
            </div>
            <!-- 입사 신청 -->
            <div v-else-if="$route.name === 'in'" class='in_container'>
                <div class="division"></div>
                <p>학생생활관 학기중 사용신청자 기본사항</p>
                <table class="in_table">
                    <tbody>
                        <tr>
                            <td class="title">성명</td>
                            <td class="content">홍길동</td>
                            <td class="title">대학</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                            <td class="title">보호자 성명</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                        </tr>
                        <tr>
                            <td class="title">학번</td>
                            <td class="content">20201234</td>
                            <td class="title">학과</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                            <td class="title">보호자 관계</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                        </tr>
                        <tr>
                            <td class="title">영문</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                            <td class="title">학적상태</td>
                            <td class="content">
                                <select name="" id="">
                                    <option value="재학">재학</option>
                                    <option value="휴학">휴학</option>
                                </select>
                            </td>
                            <td class="title">보호자 연락처</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                        </tr>
                        <tr>
                            <td class="title">한자</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                            <td class="title">연락처</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                            <td class="title">자택 전화</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                        </tr>
                        <tr>
                            <td class="title">학년/성별</td>
                            <td class="content">
                                <select name="grade">
                                    <option value="grade_one">1학년</option>
                                    <option value="grade_two">2학년</option>
                                    <option value="grade_three">3학년</option>
                                    <option value="grade_four">4학년</option>
                                </select>
                                <select name="gender">
                                    <option value="male">남자</option>
                                    <option value="female">여자</option>
                                </select>
                            </td>
                            <td class="title">이메일</td>
                            <td class="content">h20201234@hallym.ac.kr</td>
                        </tr>
                        <tr>
                            <td class="title">국적</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                            <td class="title">주소</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                        </tr>
                    </tbody>
                </table>
                <div class="division"></div>
                <PageTitle title="입사 신청" style="margin: 0 0 20px"></PageTitle>
                <form action="" id="in_apply">
                    <div class="boxes1">
                        <div class="box">
                            <div class="title">희망시설1</div>
                            <select name="" id="">
                                <option value="none">선택</option>
                                <option value="1">1관</option>
                                <option value="2">2관</option>
                                <option value="3">3관</option>
                                <option value="4">4관</option>
                                <option value="5">5관</option>
                                <option value="6">6관</option>
                                <option value="7">7관</option>
                                <option value="8">8관</option>
                            </select>
                        </div>
                        <div class="box">
                            <div class="title">희망시설2</div>
                            <select name="" id="">
                                <option value="none">선택</option>
                                <option value="1">1관</option>
                                <option value="2">2관</option>
                                <option value="3">3관</option>
                                <option value="4">4관</option>
                                <option value="5">5관</option>
                                <option value="6">6관</option>
                                <option value="7">7관</option>
                                <option value="8">8관</option>
                            </select>
                        </div>
                        <div class="box">
                            <div class="title">희망시설3</div>
                            <select name="" id="">
                                <option value="none">선택</option>
                                <option value="1">1관</option>
                                <option value="2">2관</option>
                                <option value="3">3관</option>
                                <option value="4">4관</option>
                                <option value="5">5관</option>
                                <option value="6">6관</option>
                                <option value="7">7관</option>
                                <option value="8">8관</option>
                            </select>
                        </div>
                        <div class="box">
                            <div class="title">희망시설4</div>
                            <select name="" id="">
                                <option value="none">선택</option>
                                <option value="1">1관</option>
                                <option value="2">2관</option>
                                <option value="3">3관</option>
                                <option value="4">4관</option>
                                <option value="5">5관</option>
                                <option value="6">6관</option>
                                <option value="7">7관</option>
                                <option value="8">8관</option>
                            </select>
                        </div>
                        <div class="box">
                            <div class="title">희망시설5</div>
                            <select name="" id="">
                                <option value="none">선택</option>
                                <option value="1">1관</option>
                                <option value="2">2관</option>
                                <option value="3">3관</option>
                                <option value="4">4관</option>
                                <option value="5">5관</option>
                                <option value="6">6관</option>
                                <option value="7">7관</option>
                                <option value="8">8관</option>
                            </select>
                        </div>
                        <div class="box">
                            <div class="title">희망시설6</div>
                            <select name="" id="">
                                <option value="none">선택</option>
                                <option value="1">1관</option>
                                <option value="2">2관</option>
                                <option value="3">3관</option>
                                <option value="4">4관</option>
                                <option value="5">5관</option>
                                <option value="6">6관</option>
                                <option value="7">7관</option>
                                <option value="8">8관</option>
                            </select>
                        </div>
                    </div>
                    <p class="info_red">※ 시설에 따라 입사관리비의 차이가 있으므로 필히 시설 내용을 확인 후 신청 해 주시기 바랍니다. (입사 희망 시설 나열 / 중복신청 불가 / 남학생 5지망, 여학생 6지망 선택)</p>
                    <div class="boxes2">
                        <div class="box">
                            <div class="title">사용기간</div>
                            <select name="" id="">
                                <option value="none">선택</option>
                            </select>
                        </div>
                        <div class="box">
                            <div class="title">1인실 사용신청</div>
                            <select name="" id="">
                                <option value="none">신청안함</option>
                            </select>
                        </div>
                        <div class="box">
                            <div class="title">우선입사 대상자</div>
                            <select name="" id="">
                                <option value="none">해당없음</option>
                            </select>
                        </div>
                        <div class="box">
                            <div class="title">외국인 룸메이트</div>
                            <select name="" id="">
                                <option value="none">신청안함</option>
                            </select>
                        </div>
                        <div class="box">
                            <div class="title">동반 입실자</div>
                            <input type="text">
                        </div>
                        <div class="box">
                            <div class="title">보호자 문자 수신사항</div>
                            <select name="" id="">
                                <option value="none">신청안함</option>
                            </select>
                        </div>
                    </div>
                    <input type="submit" value="신청하기">
                </form>
                <div class="division"></div>
                <div class="info_box">
                    <p class="info_red">[기타 고지사항]</p>
                    <p> 1. 본인 및 보호자의 정보 변경 발생 즉시 통합정보시스템의 개인정보를 직접 수정 바람. 
                    </p>
                    <p> 2. 기숙사비 감면(장학대상): 기숙사비 감면(장학대상)학생도 납부해야 할 금액(자치회비) 있음. 납부기간 내 필히 납부 바람.
                    (미납부=입사포기 간주)</p>
                    <p> 3. 퇴사자에게는 학생생활관 규정 제23조(납입금환불) 의거하여 환불 함.</p>
                    <table border="1">
                        <tr>
                            <td></td>
                            <td>퇴사구분</td>
                            <td>입사관리비</td>
                            <td>비고</td>
                        </tr>
                        <tr>
                            <td>지정 입사일 이전</td>
                            <td>8일 전까지<br/>일전부터 지정입사일 이전까지</td>
                            <td>전액환불<br/>입사관리비의 90%환불</td>
                            <td>*환불 시 일 수 산출의 1일 기준 금액 =<br/>학기별납입금/학기수업일수 (백원미만절상)</td>
                        </tr>
                        <tr>
                            <td>지정 입사일 후</td>
                            <td>8주 이전</td>
                            <td>입사관리비의 30% 공제 후 일 수 산출하여 환불</td>
                            <td>최종퇴사일은 퇴사신고 또는 KEY(출입키<br/>반납일을 기준으로 산출함)</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>9주 이후</td>
                            <td>환불하지 않음<br/>
                                질병 및 사고(진단서 첨부: 퇴사일 기준 15일 이내 발행), 취업<br/>
                                (실습)에 의한 퇴사 시 입사관리비의 30% 공제 후 일 수 산출하여<br/>
                                환불(증빙서류 미제출 시 환불 없음)
                            </td>
                            <td>비고</td>
                        </tr>
                    </table>
                </div>
            </div>
            <!-- 퇴사 신청 -->
            <div v-else-if="$route.name === 'out'" class='out_container'>
                <div class="division"></div>
                <p>학생생활관 학기중 사용신청자 기본사항</p>
                <table class="in_table">
                    <tbody>
                        <tr>
                            <td class="title">성명</td>
                            <td class="content">홍길동</td>
                            <td class="title">대학</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                            <td class="title">보호자 성명</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                        </tr>
                        <tr>
                            <td class="title">학번</td>
                            <td class="content">20201234</td>
                            <td class="title">학과</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                            <td class="title">보호자 관계</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                        </tr>
                        <tr>
                            <td class="title">영문</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                            <td class="title">학적상태</td>
                            <td class="content">
                                <select name="" id="">
                                    <option value="재학">재학</option>
                                    <option value="휴학">휴학</option>
                                </select>
                            </td>
                            <td class="title">보호자 연락처</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                        </tr>
                        <tr>
                            <td class="title">한자</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                            <td class="title">연락처</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                            <td class="title">자택 전화</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                        </tr>
                        <tr>
                            <td class="title">학년/성별</td>
                            <td class="content">
                                <select name="grade">
                                    <option value="grade_one">1학년</option>
                                    <option value="grade_two">2학년</option>
                                    <option value="grade_three">3학년</option>
                                    <option value="grade_four">4학년</option>
                                </select>
                                <select name="gender">
                                    <option value="male">남자</option>
                                    <option value="female">여자</option>
                                </select>
                            </td>
                            <td class="title">이메일</td>
                            <td class="content">h20201234@hallym.ac.kr</td>
                        </tr>
                        <tr>
                            <td class="title">국적</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                            <td class="title">주소</td>
                            <td class="content"><input type="text" placeholder="입력해주세요"></td>
                        </tr>
                    </tbody>
                </table>
                <div class="division"></div>
                <form action="" id="out_apply">
                    <div class="box">
                        <div class="title">퇴사 예정일</div>
                        <select name="" id="">
                            <option value="none">선택</option>
                        </select>
                    </div>
                    <div class="box">
                        <div class="title">퇴사 사유</div>
                        <textarea name="" id="" cols="30" rows="10"></textarea>
                    </div>
                    <input type="submit" value="신청하기">
                </form>
                <div class="division"></div>
                <div class="info_box">
                    <p class="info_red">[기타 고지사항]</p>
                    <p> 1. 본인 및 보호자의 정보 변경 발생 즉시 통합정보시스템의 개인정보를 직접 수정 바람. 
                    </p>
                    <p> 2. 기숙사비 감면(장학대상): 기숙사비 감면(장학대상)학생도 납부해야 할 금액(자치회비) 있음. 납부기간 내 필히 납부 바람.
                    (미납부=입사포기 간주)</p>
                    <p> 3. 퇴사자에게는 학생생활관 규정 제23조(납입금환불) 의거하여 환불 함.</p>
                    <table border="1">
                        <tr>
                            <td></td>
                            <td>퇴사구분</td>
                            <td>입사관리비</td>
                            <td>비고</td>
                        </tr>
                        <tr>
                            <td>지정 입사일 이전</td>
                            <td>8일 전까지<br/>일전부터 지정입사일 이전까지</td>
                            <td>전액환불<br/>입사관리비의 90%환불</td>
                            <td>*환불 시 일 수 산출의 1일 기준 금액 =<br/>학기별납입금/학기수업일수 (백원미만절상)</td>
                        </tr>
                        <tr>
                            <td>지정 입사일 후</td>
                            <td>8주 이전</td>
                            <td>입사관리비의 30% 공제 후 일 수 산출하여 환불</td>
                            <td>최종퇴사일은 퇴사신고 또는 KEY(출입키<br/>반납일을 기준으로 산출함)</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>9주 이후</td>
                            <td>환불하지 않음<br/>
                                질병 및 사고(진단서 첨부: 퇴사일 기준 15일 이내 발행), 취업<br/>
                                (실습)에 의한 퇴사 시 입사관리비의 30% 공제 후 일 수 산출하여<br/>
                                환불(증빙서류 미제출 시 환불 없음)
                            </td>
                            <td>비고</td>
                        </tr>
                    </table>
                </div>
            </div>
            <!-- 외박 신청 -->
            <div v-else-if="$route.name === 'sleep'" class='sleep_container'>
                <div class="box_blue"></div>
                <form id="sleep_apply" action="">
                    <table>
                        <tr>
                            <td class="title">신청 사유</td>
                            <td class="content">
                                <input type="text" v-model="reason" placeholder="Ex). 본가 방문 (20자 이내)" maxlength="20">
                            </td>
                        </tr>
                        <tr>
                            <td class="title">신청 날짜</td>
                            <td class="content">
                                <input type="date" 
                                v-model="date_sleepout" 
                                data-placeholder="2022-07-25 (달력에서 선택하게 달력 삽입)"
                                required 
                                aria-required="true"
                                className={styles.selectDay}
                                >
                            </td>
                        </tr>
                    </table>
                    <input class="submit_btn" type="button" value="신청하기" @click="creatSleepOut()">
                </form>
            </div>
            <div v-else>
                error_page
            </div>
        </div>
    </div>  
</div>
    
</template>

<script>
import SidebarCom from '@/components/SidebarCom.vue';
import PageTitle from "@/components/PageTitle.vue";
import ApplySleepDataService from "@/services/ApplySleepDataService";

export default {
    data() {
        return {
            title: "String",
            pageName: "신청 및 예약",
            // 사이드바 정보
            side: [
                { title: "상담 및 면담신청", path: "/reserve", active: true},
                { title: "스터디룸 예약", path: "/reserve/study" },
                {
                    title: "입사/퇴사 신청",
                    path: "/reserve/in",
                    semi: true,
                    semiTitle: [
                        { title: "입사신청", path: "/reserve/in", active: false },
                        { title: "퇴사신청", path: "/reserve/out", active: false },
                    ],
                },
                { title: "외박 신청", path: "/reserve/sleep" },
                
            ],
            timeCount: 0,
            timeTable: [true, false, false],
            seat: [
                { no: 1, status: true, isActive: false},
                { no: 2, status: false, isActive: false},
                { no: 3, status: false, isActive: false},
                { no: 4, status: true, isActive: false},
                { no: 5, status: true, isActive: false},
                { no: 6, status: true, isActive: false},
                { no: 7, status: false, isActive: false},
                { no: 8, status: true, isActive: false},
                { no: 9, status: true, isActive: false},
                { no: 10, status: false, isActive: false},
            ],
            reason: "",
            date_sleepout: "",
            studentno: "",
        }
    },
    components: { SidebarCom, PageTitle},
    created() {
        this.routeCheck()
    },
    methods: {
        routeCheck() {
            this.activeReset();
            this.side[2].semi = false;
            if (this.$route.name === "consult") {
                this.title = "신청 및 예약 > 상담 및 면담 신청";
                this.side[0].active = true;
            }  else if (this.$route.name === "study") {
                this.title = "신청 및 예약 > 스터디룸 예약";
                this.side[1].active = true;
            } else if (this.$route.name === "in") {
                this.title = "신청 및 예약 > 입사 신청";
                this.side[2].active = true;
                this.side[2].semiTitle[0].active = true;
                this.side[2].semiTitle[1].active = false;
                this.side[2].semi = true;
            } else if (this.$route.name === "out") {
                this.title = "신청 및 예약 > 퇴사 신청";
                this.side[2].active = true;
                this.side[2].semiTitle[0].active = false;
                this.side[2].semiTitle[1].active = true;
                this.side[2].semi = true;
            }  else if (this.$route.name === "sleep") {
                this.title = "신청 및 예약 > 외박 신청";
                this.side[3].active = true;
            } else {
                this.title = "Error Page";
            }
        },
        activeReset() {
            for (var i = 0; i < this.side.length; i++) {
                this.side[i].active = false;
            }
        },
        addTime() {
            this.timeCount += 1;
            this.timeTable[this.timeCount] = true;
        },
        subTime() {
            this.timeTable[this.timeCount] = false;
            this.timeCount -= 1;
        },
        myFilter(index) {
            for (const item in this.seat) {
                this.seat[item].isActive = false;
            }
            this.seat[index].isActive = !this.seat[index].isActive;
        },
        creatSleepOut(){
            let data = {
                date_sleepout: this.date_sleepout,
                reason: this.reason
            }
            ApplySleepDataService.create(this.user.studentno, data)
            alert("신청 완료했습니다.");
        }
    },
    computed: {
        today() {
            const date = new Date();
            return date.toLocaleDateString();
        },
        selectedSeat() {
            let no = ''
            for (const item in this.seat) {
                if(this.seat[item].isActive == true) {
                    no = this.seat[item].no
                }
            }
            return no
        },
        user() {
            return this.$store.state.auth.user
        }
    },  
    watch: {
        $route(to) {
            this.routeCheck();
            if (to.path == "/reserve/in" || to.path == "/reserve/out") {
                this.side[2].semi = true;
                if (to.path == "/reserve/in") {
                this.side[2].semiTitle[0].active = true;
                this.side[2].semiTitle[1].active = false;
                } else {
                this.side[2].semiTitle[1].active = true;
                this.side[2].semiTitle[0].active = false;
                }
            } else {
                for (var i = 0; i < this.side.length; i++) {
                this.side[i].semi = false;
                }
            }
        },
    },
}
</script>

<style lang="less" scoped>
.wrapper {
    margin: 100px 0 300px;
    display: flex;
    .left_container {
        width: 18%;
    }
    .right_container {
        width: 82%;
        .content_wrap {
            .consult_container {
                .consult_table {
                    border-top: 1px solid #C0C0C0;
                    tr {
                        td {
                            padding: 20px;
                            border: 1px solid #C0C0C0;
                            border-collapse: collapse;
                        }
                        td:first-child, td:nth-child(3) {
                            background-color: #D9D9D9;
                            font-weight: 600;
                            text-align: center;
                        }
                    }
                    tr:nth-child(6) {
                        border: none;
                        td {
                            border: none;
                            padding: 20px 0;
                            input[type="submit"] {
                                float: right;
                                background-color: #336EB4;
                                color: #fff;
                                font-size: 14px;
                                padding: 16px;
                                border: none;
                                &:hover {
                                    cursor: pointer;
                                }
                            }
                        }
                    }
                    .time_table {
                        width: 100%;
                        caption {
                            font-weight: 700;
                            font-size: 20px;
                            margin-bottom: 10px;
                        }
                        tr, td {
                            text-align: center;
                            border: 1px solid #C0C0C0 !important;
                        }
                        td:nth-child(3) {
                            background-color: #fff;
                        }
                        tr:nth-child(2) {
                            td {
                                background-color: #EFEFEF;
                                font-weight: 300;
                            }
                        }
                    }
                }
                
            }
            .study_container {
                .reserve_box {
                    .title {
                        color: #336EB4;
                        margin-bottom: 10px;
                        font-weight: 700;
                        font-size: 18px;
                        p {
                            display: inline-block;
                            font-weight: 300;
                        }
                    }
                    .seat_status {
                        margin-bottom: 30px;
                        .seat {
                            padding: 10px 16px;
                            background-color: #fff;
                            color: #336EB4;
                            &.active {
                                background-color: #336EB4;
                                color: #fff;
                            }
                            &.disable {
                                border-color: #858585;
                                color: #fff;
                                background-color: #858585;
                                cursor:auto;
                            }
                        }
                    }
                    select {
                        padding: 14px;
                        border: 1px solid #858585;
                        border-radius: 0;
                        color: #858585;
                        margin-right: 3px;
                        option {
                            font-size: 16px;
                            &[value=""][disabled] {
                                display: none;
                            }
                        }
                    }
                    input[type="button"] {
                        border: 3px solid #336EB4;
                        border-radius: 5px;
                        margin-left: 10px;
                        padding: 5px 12px;
                        line-height: 24px;
                        font-size: 20px;
                        font-weight: 900;
                        text-align: center;
                        color: #336EB4;
                        &:hover {
                            cursor: pointer;
                        }
                    }
                    input[type="submit"] {
                        display: block;
                        background-color: #336EB4;
                        color: #fff;
                        font-size: 14px;
                        padding: 16px;
                        margin-top: 200px;
                        border: none;
                        &:hover {
                            cursor: pointer;
                        }
                    }
                }
            }
            .in_container {
                .division {
                    width: 100%;
                    height: 1px;
                    background-color: #858585;
                    margin: 40px 0;
                }
                .in_table {
                    td {
                        padding: 24px 10px;
                        text-align: center;
                        font-weight: 600;
                        border: 1px solid #C0C0C0;
                        &.title {
                            background-color: #336EB4;
                            color: #fff;
                            width: 100px;
                        }
                        &.content {
                            color: #336EB4;
                            input {
                                border: none;
                                &::placeholder {
                                    text-align: center;
                                }
                                &:focus::-webkit-input-placeholder { 
                                    color:transparent;
                                }
                            }
                        }
                    }
                }
                #in_apply {
                    margin-bottom: 100px;
                    .boxes1 {
                        display: flex;
                        justify-content: space-between;
                        flex-wrap: wrap;
                        .box {
                            display: flex;
                            justify-content: space-between;
                            width: 30%;
                            height: 50px;
                            margin-bottom: 20px;
                            font-size: 16px;
                            font-weight: 600;
                            .title {
                                width: 50%;
                                line-height: 55px;
                                text-align: center;
                                background-color: #336EB4;
                                color: #fff;
                            }
                            select {
                                width: 45%;
                                text-align: center;
                                font-size: 16px;
                            }
                        }
                    }
                }
                .info_red {
                    color: #FF0000;
                    font-size: 14px;
                    font-weight: 800;
                } 
                .boxes2 {
                    display: flex;
                    justify-content: space-between;
                    flex-wrap: wrap;
                    .box {
                        width: 33%;
                        display: flex;
                        justify-content: space-between;
                        font-size: 16px;
                        font-weight: 600;
                        margin-bottom: 20px;
                        .title {
                            width: 58%;
                            line-height: 55px;
                            text-align: center;
                            background-color: #336EB4;
                            color: #fff;
                        }
                        select {
                            width: 40%;
                            text-align: center;
                            font-size: 16px;
                        }
                        input[type=text] {
                            width: 70%;
                        }
                    }
                }
                input[type=submit] {
                    float: right;
                    background-color: #336EB4;
                    color: #fff;
                    font-size: 16px;
                    padding: 18px;
                    border: none;
                    display: block;
                    &:hover {
                        cursor: pointer;
                    }
                }
            }
            .info_box {
                font-size: 14px;
            }
        }
        .out_container {
            .division {
                width: 100%;
                height: 1px;
                background-color: #858585;
                margin: 40px 0;
            }
            .in_table {
                td {
                    padding: 24px 10px;
                    text-align: center;
                    font-weight: 600;
                    border: 1px solid #C0C0C0;
                    &.title {
                        background-color: #336EB4;
                        color: #fff;
                        width: 100px;
                    }
                    &.content {
                        color: #336EB4;
                        input {
                            border: none;
                            &::placeholder {
                                text-align: center;
                            }
                            &:focus::-webkit-input-placeholder { 
                                color:transparent;
                            }
                        }
                    }
                }
            }
            #out_apply {
                margin-bottom: 100px;
                .box {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                    margin-bottom: 10px;
                    .title {
                        width: 20%;
                        text-align: center;
                        background-color: #336EB4;
                        color: #fff;
                        width: 20%;
                        line-height: 55px;
                        
                    }
                    &:nth-child(2) {
                        .title {
                            height: 200px;
                            line-height: 200px;
                        }
                    }
                    select {
                        width: 78.8%;
                        height: 54px;
                        border-radius: 0;
                        border: 1px solid #C0C0C0;
                    }
                    textarea {
                        width: 78%;
                        height: 194px;
                        border-radius: 0;
                        border: 1px solid #C0C0C0;
                    }
                }
            }
            .info_red {
                color: #FF0000;
                font-size: 14px;
                font-weight: 800;
            } 
            .boxes2 {
                display: flex;
                justify-content: space-between;
                flex-wrap: wrap;
                .box {
                    width: 33%;
                    display: flex;
                    justify-content: space-between;
                    font-size: 16px;
                    font-weight: 600;
                    margin-bottom: 20px;
                    .title {
                        width: 58%;
                        line-height: 55px;
                        text-align: center;
                        background-color: #336EB4;
                        color: #fff;
                    }
                    select {
                        width: 40%;
                        text-align: center;
                        font-size: 16px;
                    }
                    input[type=text] {
                        width: 70%;
                    }
                }
            }
            input[type=submit] {
                float: right;
                background-color: #336EB4;
                color: #fff;
                font-size: 16px;
                padding: 18px;
                border: none;
                display: block;
                &:hover {
                    cursor: pointer;
                }
            }
            .info_box {
                font-size: 14px;
            }
        }
        .sleep_container {
            .box_blue {
                background: #336EB4;
                height: 30px;
                width: 100%;
                margin-bottom: 10px;
            }
            #sleep_apply {
                table {
                    width: 100%;
                    tr {
                        .title {
                            width: 20%;
                            font-weight: 800;
                            padding: 14px 8px;
                        }
                        .content {
                            input[type="text"] {
                                width: 90%;
                                border: none;
                                background: #DBECFF;
                                padding: 5px;
                            }
                            input[type='date'] {
                                background: #EBECF0;
                                color: #858585;
                                border: none;
                                padding: 4px;
                                width: 50%;
                                &::before {
                                    content: attr(data-placeholder);
                                    width: 100%;
                                }
                                &:focus::before,
                                &:valid::before {
                                    display: none;
                                }
                            }
                        }
                    }
                }
                .submit_btn {
                    float: right;
                    background-color: #336EB4;
                    color: #fff;
                    font-size: 14px;
                    padding: 16px;
                    border: none;
                    margin-top: 80px;
                    &:hover {
                        cursor: pointer;
                    }
                }
            }
            
        }
        
    }
}
</style>