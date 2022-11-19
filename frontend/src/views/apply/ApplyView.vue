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
                    <!-- <div v-if="this.user_approved==true"> -->
                    <form action="">
                        <table class="consult_table">
                            <tr>
                                <td>이름</td>
                                <td>{{ this.user.name }}</td>
                                <td>학번</td>
                                <td>{{ this.user.studentno }}</td>
                            </tr>
                            <tr>
                                <td>연락처</td>
                                <td>{{ this.userinfo.phone }}
                                </td>
                                <td>이메일</td>
                                <td>{{ this.user.email }}
                                </td>
                            </tr>
                            <tr>
                                <td>상담분야</td>
                                <td>
                                    <select name="" id="" v-model="consult_topic">
                                        <option value="교우관계">교우관계</option>
                                        <option value="학업">학업</option>
                                        <option value="경제">경제</option>
                                    </select>
                                </td>
                                <td>신청일자</td>
                                <td>{{ today }}</td>
                            </tr>
                            <tr>
                                <td>상담내용</td>
                                <td colspan="3">
                                    <input type="text" size="100" maxlength="100" v-model="consult_subject"
                                        placeholder="100자 이내로 입력하세요">
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
                                            <td><input type="checkbox" name="consult" id="m1" v-model="mon_timeslot"
                                                    value="1"></td>
                                            <td><input type="checkbox" name="consult" id="t1" v-model="tue_timeslot"
                                                    value="1"></td>
                                            <td><input type="checkbox" name="consult" id="w1" v-model="wed_timeslot"
                                                    value="1"></td>
                                            <td><input type="checkbox" name="consult" id="r1" v-model="thu_timeslot"
                                                    value="1"></td>
                                            <td><input type="checkbox" name="consult" id="f1" v-model="fri_timeslot"
                                                    value="1"></td>
                                        </tr>
                                        <tr>
                                            <td>10~11</td>
                                            <td><input type="checkbox" name="consult" id="m2" v-model="mon_timeslot"
                                                    value="2"></td>
                                            <td><input type="checkbox" name="consult" id="t2" v-model="tue_timeslot"
                                                    value="2"></td>
                                            <td><input type="checkbox" name="consult" id="w2" v-model="wed_timeslot"
                                                    value="2"></td>
                                            <td><input type="checkbox" name="consult" id="r2" v-model="thu_timeslot"
                                                    value="2"></td>
                                            <td><input type="checkbox" name="consult" id="f2" v-model="fri_timeslot"
                                                    value="2"></td>
                                        </tr>
                                        <tr>
                                            <td>11~12</td>
                                            <td><input type="checkbox" name="consult" id="m3" v-model="mon_timeslot"
                                                    value="3"></td>
                                            <td><input type="checkbox" name="consult" id="t3" v-model="tue_timeslot"
                                                    value="3"></td>
                                            <td><input type="checkbox" name="consult" id="w3" v-model="wed_timeslot"
                                                    value="3"></td>
                                            <td><input type="checkbox" name="consult" id="r3" v-model="thu_timeslot"
                                                    value="3"></td>
                                            <td><input type="checkbox" name="consult" id="f3" v-model="fri_timeslot"
                                                    value="3"></td>
                                        </tr>
                                        <tr>
                                            <td>12~13</td>
                                            <td colspan="5">점심시간</td>
                                        </tr>
                                        <tr>
                                            <td>13~14</td>
                                            <td><input type="checkbox" name="consult" id="m4" v-model="mon_timeslot"
                                                    value="4"></td>
                                            <td><input type="checkbox" name="consult" id="t4" v-model="tue_timeslot"
                                                    value="4"></td>
                                            <td><input type="checkbox" name="consult" id="w4" v-model="wed_timeslot"
                                                    value="4"></td>
                                            <td><input type="checkbox" name="consult" id="r4" v-model="thu_timeslot"
                                                    value="4"></td>
                                            <td><input type="checkbox" name="consult" id="f4" v-model="fri_timeslot"
                                                    value="4"></td>
                                        </tr>
                                        <tr>
                                            <td>14~15</td>
                                            <td><input type="checkbox" name="consult" id="m5" v-model="mon_timeslot"
                                                    value="5"></td>
                                            <td><input type="checkbox" name="consult" id="t5" v-model="tue_timeslot"
                                                    value="5"></td>
                                            <td><input type="checkbox" name="consult" id="w5" v-model="wed_timeslot"
                                                    value="5"></td>
                                            <td><input type="checkbox" name="consult" id="r5" v-model="thu_timeslot"
                                                    value="5"></td>
                                            <td><input type="checkbox" name="consult" id="f5" v-model="fri_timeslot"
                                                    value="5"></td>
                                        </tr>
                                        <tr>
                                            <td>15~16</td>
                                            <td><input type="checkbox" name="consult" id="m6" v-model="mon_timeslot"
                                                    value="6"></td>
                                            <td><input type="checkbox" name="consult" id="t6" v-model="tue_timeslot"
                                                    value="6"></td>
                                            <td><input type="checkbox" name="consult" id="w6" v-model="wed_timeslot"
                                                    value="6"></td>
                                            <td><input type="checkbox" name="consult" id="r6" v-model="thu_timeslot"
                                                    value="6"></td>
                                            <td><input type="checkbox" name="consult" id="f6" v-model="fri_timeslot"
                                                    value="6"></td>
                                        </tr>
                                        <tr>
                                            <td>16~17</td>
                                            <td><input type="checkbox" name="consult" id="m7" v-model="mon_timeslot"
                                                    value="7"></td>
                                            <td><input type="checkbox" name="consult" id="t7" v-model="tue_timeslot"
                                                    value="7"></td>
                                            <td><input type="checkbox" name="consult" id="w7" v-model="wed_timeslot"
                                                    value="7"></td>
                                            <td><input type="checkbox" name="consult" id="r7" v-model="thu_timeslot"
                                                    value="7"></td>
                                            <td><input type="checkbox" name="consult" id="f7" v-model="fri_timeslot"
                                                    value="7"></td>
                                        </tr>
                                        <tr>
                                            <td>17~18</td>
                                            <td><input type="checkbox" name="consult" id="m8" v-model="mon_timeslot"
                                                    value="8"></td>
                                            <td><input type="checkbox" name="consult" id="t8" v-model="tue_timeslot"
                                                    value="8"></td>
                                            <td><input type="checkbox" name="consult" id="w8" v-model="wed_timeslot"
                                                    value="8"></td>
                                            <td><input type="checkbox" name="consult" id="r8" v-model="thu_timeslot"
                                                    value="8"></td>
                                            <td><input type="checkbox" name="consult" id="f8" v-model="fri_timeslot"
                                                    value="8"></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4" style="background-color:#fff">
                                    <input class="submit_btn" type="button" value="신청하기" @click="reserveConsult()">
                                </td>
                            </tr>
                        </table>
                    </form>
                    <!-- </div>
                    <div v-else="alertMsg()"></div> -->
                </div>
                <!-- 스터디룸 예약 -->
                <div v-else-if="$route.name === 'study'" class='study_container'>
                    <form action="" class="reserve_box">
                        <div class="title">선택좌석 (좌석도 삽입예정)</div>
                        <div class="seat_status">
                            <input type="button" v-for="(seat, index) in this.seat" :key="seat.no" class="seat"
                                :class="{ active: seat.isActive, disable: !seat.status }" @click="myFilterSeat(index)"
                                :disabled="!seat.status" :value="seat.no">
                        </div>
                        <div class="title">
                            이용시간
                            <p>(하루 최대 이용 시간은 6시간입니다.)</p>
                            <strong style="margin-left: 50px;">TODAY : {{today}}</strong>
                        </div>

                        <div v-if="this.seat[0].isActive == true">
                            <div class="time_status">
                                <input type="button" v-for="(timeslotList, index) in this.timeslotList"
                                    :key="timeslotList.time" class="timeslotList"
                                    :class="{ active: timeslotList.isActive, disable: studyroom_status_list1[index] }"
                                    @click="myFilterTime(index)" :disabled="studyroom_status_list1[index]"
                                    :value="timeslotList.time">
                            </div>
                        </div>
                        <div v-else-if="this.seat[1].isActive == true">
                            <div class="time_status">
                                <input type="button" v-for="(timeslotList, index) in this.timeslotList"
                                    :key="timeslotList.time" class="timeslotList"
                                    :class="{ active: timeslotList.isActive, disable: studyroom_status_list2[index] }"
                                    @click="myFilterTime(index)" :disabled="studyroom_status_list2[index]"
                                    :value="timeslotList.time">
                            </div>
                        </div>
                        <div v-else-if="this.seat[2].isActive == true">
                            <div class="time_status">
                                <input type="button" v-for="(timeslotList, index) in this.timeslotList"
                                    :key="timeslotList.time" class="timeslotList"
                                    :class="{ active: timeslotList.isActive, disable: studyroom_status_list3[index] }"
                                    @click="myFilterTime(index)" :disabled="studyroom_status_list3[index]"
                                    :value="timeslotList.time">
                            </div>
                        </div>
                        <div v-else-if="this.seat[3].isActive == true">
                            <div class="time_status">
                                <input type="button" v-for="(timeslotList, index) in this.timeslotList"
                                    :key="timeslotList.time" class="timeslotList"
                                    :class="{ active: timeslotList.isActive, disable: studyroom_status_list4[index] }"
                                    @click="myFilterTime(index)" :disabled="studyroom_status_list4[index]"
                                    :value="timeslotList.time">
                            </div>
                        </div>
                        <div v-else-if="this.seat[4].isActive == true">
                            <div class="time_status">
                                <input type="button" v-for="(timeslotList, index) in this.timeslotList"
                                    :key="timeslotList.time" class="timeslotList"
                                    :class="{ active: timeslotList.isActive, disable: studyroom_status_list5[index] }"
                                    @click="myFilterTime(index)" :disabled="studyroom_status_list5[index]"
                                    :value="timeslotList.time">
                            </div>
                        </div>
                        <div v-else-if="this.seat[5].isActive == true">
                            <div class="time_status">
                                <input type="button" v-for="(timeslotList, index) in this.timeslotList"
                                    :key="timeslotList.time" class="timeslotList"
                                    :class="{ active: timeslotList.isActive, disable: studyroom_status_list6[index] }"
                                    @click="myFilterTime(index)" :disabled="studyroom_status_list6[index]"
                                    :value="timeslotList.time">
                            </div>
                        </div>
                        <div v-else-if="this.seat[6].isActive == true">
                            <div class="time_status">
                                <input type="button" v-for="(timeslotList, index) in this.timeslotList"
                                    :key="timeslotList.time" class="timeslotList"
                                    :class="{ active: timeslotList.isActive, disable: studyroom_status_list7[index] }"
                                    @click="myFilterTime(index)" :disabled="studyroom_status_list7[index]"
                                    :value="timeslotList.time">
                            </div>
                        </div>
                        <div v-else-if="this.seat[7].isActive == true">
                            <div class="time_status">
                                <input type="button" v-for="(timeslotList, index) in this.timeslotList"
                                    :key="timeslotList.time" class="timeslotList"
                                    :class="{ active: timeslotList.isActive, disable: studyroom_status_list8[index] }"
                                    @click="myFilterTime(index)" :disabled="studyroom_status_list7[index]"
                                    :value="timeslotList.time">
                            </div>
                        </div>
                        <div v-else-if="this.seat[8].isActive == true">
                            <div class="time_status">
                                <input type="button" v-for="(timeslotList, index) in this.timeslotList"
                                    :key="timeslotList.time" class="timeslotList"
                                    :class="{ active: timeslotList.isActive, disable: studyroom_status_list9[index] }"
                                    @click="myFilterTime(index)" :disabled="studyroom_status_list7[index]"
                                    :value="timeslotList.time">
                            </div>
                        </div>
                        <div v-else-if="this.seat[9].isActive == true">
                            <div class="time_status">
                                <input type="button" v-for="(timeslotList, index) in this.timeslotList"
                                    :key="timeslotList.time" class="timeslotList"
                                    :class="{ active: timeslotList.isActive, disable: studyroom_status_list10[index] }"
                                    @click="myFilterTime(index)" :disabled="studyroom_status_list7[index]"
                                    :value="timeslotList.time">
                            </div>
                        </div>
                        <input class="submit_btn" type="button" value="예약하기" @click="reserveStudyroom()">
                    </form>
                </div>
                <!-- 입사 신청 -->
                <div v-else-if="$route.name === 'in'" class='in_container'>
                    <div class="division"></div>
                    <p>학생생활관 학기중 사용신청자 기본사항</p>
                    <form action="">
                        <table class="in_table">
                            <tbody>
                                <tr>
                                    <td class="title">성명</td>
                                    <td class="content">{{ this.user.name }}</td>
                                    <td class="title">대학</td>
                                    <td class="content"><input type="text" v-model="department" placeholder="입력해주세요"
                                            required></td>
                                    <td class="title">보호자 성명</td>
                                    <td class="content"><input type="text" v-model="guardian_name" placeholder="입력해주세요">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="title">학번</td>
                                    <td class="content">{{ this.user.studentno }}</td>
                                    <td class="title">학과</td>
                                    <td class="content"><input type="text" v-model="major" placeholder="입력해주세요"></td>
                                    <td class="title">보호자 관계</td>
                                    <td class="content"><input type="text" v-model="guardian_relation"
                                            placeholder="입력해주세요"></td>
                                </tr>
                                <tr>
                                    <td class="title">영문</td>
                                    <td class="content"><input type="text" v-model="english_name" placeholder="입력해주세요">
                                    </td>
                                    <td class="title">학적상태</td>
                                    <td class="content">
                                        <select name="" id="" v-model="student_status">
                                            <option value="재학">재학</option>
                                            <option value="휴학">휴학</option>
                                        </select>
                                    </td>
                                    <td class="title">보호자 연락처</td>
                                    <td class="content"><input type="text" v-model="guardian_phone"
                                            placeholder="입력해주세요"></td>
                                </tr>
                                <tr>
                                    <td class="title">한자</td>
                                    <td class="content"><input type="text" v-model="chinese_name" placeholder="입력해주세요">
                                    </td>
                                    <td class="title">연락처</td>
                                    <td class="content"><input type="text" v-model="phone" placeholder="입력해주세요"></td>
                                    <td class="title">자택 전화</td>
                                    <td class="content"><input type="text" v-model="landline" placeholder="입력해주세요"></td>
                                </tr>
                                <tr>
                                    <td class="title">학년/성별</td>
                                    <td class="content">
                                        <select name="grade" v-model="grade">
                                            <option value=1>1학년</option>
                                            <option value=2>2학년</option>
                                            <option value=3>3학년</option>
                                            <option value=4>4학년</option>
                                        </select>
                                        <select name="gender" v-model="gender">
                                            <option value="m">남자</option>
                                            <option value="f">여자</option>
                                        </select>
                                    </td>
                                    <td class="title">이메일</td>
                                    <td class="content">{{ this.user.email }}</td>
                                </tr>
                                <tr>
                                    <td class="title">국적</td>
                                    <td class="content"><input type="text" v-model="nationality" placeholder="입력해주세요">
                                    </td>
                                    <td class="title">주소</td>
                                    <td class="content"><input type="text" v-model="address" placeholder="입력해주세요"></td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                    <div class="division"></div>
                    <PageTitle title="입사 신청" style="margin: 0 0 20px"></PageTitle>
                    <form action="" id="in_apply">
                        <div class="boxes1">
                            <div class="box">
                                <div class="title">희망시설1</div>
                                <select name="" id="" v-model="hope_fac_1">
                                    <option value="none">선택</option>
                                    <option value=1>1관</option>
                                    <option value=2>2관</option>
                                    <option value=3>3관</option>
                                    <option value=4>4관</option>
                                    <option value=5>5관</option>
                                    <option value=6>6관</option>
                                    <option value=7>7관</option>
                                    <option value=8>8관</option>
                                </select>
                            </div>
                            <div class="box">
                                <div class="title">희망시설2</div>
                                <select name="" id="" v-model="hope_fac_2">
                                    <option value="none">선택</option>
                                    <option value=1>1관</option>
                                    <option value=2>2관</option>
                                    <option value=3>3관</option>
                                    <option value=4>4관</option>
                                    <option value=5>5관</option>
                                    <option value=6>6관</option>
                                    <option value=7>7관</option>
                                    <option value=8>8관</option>
                                </select>
                            </div>
                            <div class="box">
                                <div class="title">희망시설3</div>
                                <select name="" id="" v-model="hope_fac_3">
                                    <option value="none">선택</option>
                                    <option value=1>1관</option>
                                    <option value=2>2관</option>
                                    <option value=3>3관</option>
                                    <option value=4>4관</option>
                                    <option value=5>5관</option>
                                    <option value=6>6관</option>
                                    <option value=7>7관</option>
                                    <option value=8>8관</option>
                                </select>
                            </div>
                            <div class="box">
                                <div class="title">희망시설4</div>
                                <select name="" id="" v-model="hope_fac_4">
                                    <option value="none">선택</option>
                                    <option value=1>1관</option>
                                    <option value=2>2관</option>
                                    <option value=3>3관</option>
                                    <option value=4>4관</option>
                                    <option value=5>5관</option>
                                    <option value=6>6관</option>
                                    <option value=7>7관</option>
                                    <option value=8>8관</option>
                                </select>
                            </div>
                            <div class="box">
                                <div class="title">희망시설5</div>
                                <select name="" id="" v-model="hope_fac_5">
                                    <option value="none">선택</option>
                                    <option value=1>1관</option>
                                    <option value=2>2관</option>
                                    <option value=3>3관</option>
                                    <option value=4>4관</option>
                                    <option value=5>5관</option>
                                    <option value=6>6관</option>
                                    <option value=7>7관</option>
                                    <option value=8>8관</option>
                                </select>
                            </div>
                            <div class="box">
                                <div class="title">희망시설6</div>
                                <select name="" id="" v-model="hope_fac_6">
                                    <option value="none">선택</option>
                                    <option value=1>1관</option>
                                    <option value=2>2관</option>
                                    <option value=3>3관</option>
                                    <option value=4>4관</option>
                                    <option value=5>5관</option>
                                    <option value=6>6관</option>
                                    <option value=7>7관</option>
                                    <option value=8>8관</option>
                                </select>
                            </div>
                        </div>
                        <p class="info_red">※ 시설에 따라 입사관리비의 차이가 있으므로 필히 시설 내용을 확인 후 신청 해 주시기 바랍니다. (입사 희망 시설 나열 / 중복신청
                            불가 / 남학생 5지망, 여학생 6지망 선택)</p>
                        <div class="boxes2">
                            <div class="box">
                                <div class="title">사용기간</div>
                                <select name="" id="" v-model="period">
                                    <option value="1">1개월</option>
                                    <option value="2">2개월</option>
                                    <option value="3">3개월</option>
                                    <option value="6">6개월</option>
                                    <option value="12">12개월</option>
                                </select>
                            </div>
                            <div class="box">
                                <div class="title">1인실 사용신청</div>
                                <select name="" id="" v-model="single_yn">
                                    <option value="true">신청</option>
                                    <option value="false">신청안함</option>
                                </select>
                            </div>
                            <div class="box">
                                <div class="title">우선입사 대상자</div>
                                <select name="" id="" v-model="pri_ent">
                                    <option value="true">해당함</option>
                                    <option value="false">해당없음</option>
                                </select>
                            </div>
                            <div class="box">
                                <div class="title">외국인 룸메이트</div>
                                <select name="" id="" v-model="fgn_mate">
                                    <option value="true">신청</option>
                                    <option value="false">신청안함</option>
                                </select>
                            </div>
                            <div class="box">
                                <div class="title">동반 입실자</div>
                                <input type="text" v-model="accpm_ent">
                            </div>
                            <div class="box">
                                <div class="title">보호자 문자 수신사항</div>
                                <select name="" id="" v-model="par_mes_yn">
                                    <option value="true">신청</option>
                                    <option value="false">신청안함</option>
                                </select>
                            </div>
                        </div>
                        <input class="submit_btn" type="button" value="신청하기" @click="reserveJoin()">
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
                                <td>8일 전까지<br />일전부터 지정입사일 이전까지</td>
                                <td>전액환불<br />입사관리비의 90%환불</td>
                                <td>*환불 시 일 수 산출의 1일 기준 금액 =<br />학기별납입금/학기수업일수 (백원미만절상)</td>
                            </tr>
                            <tr>
                                <td>지정 입사일 후</td>
                                <td>8주 이전</td>
                                <td>입사관리비의 30% 공제 후 일 수 산출하여 환불</td>
                                <td>최종퇴사일은 퇴사신고 또는 KEY(출입키<br />반납일을 기준으로 산출함)</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>9주 이후</td>
                                <td>환불하지 않음<br />
                                    질병 및 사고(진단서 첨부: 퇴사일 기준 15일 이내 발행), 취업<br />
                                    (실습)에 의한 퇴사 시 입사관리비의 30% 공제 후 일 수 산출하여<br />
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
                                <td class="content">{{ this.user.name }}</td>
                                <td class="title">대학</td>
                                <td class="content">{{ this.userinfo.department }}</td>
                                <td class="title">보호자 성명</td>
                                <td class="content">{{ this.userinfo.guardian_name }}</td>
                            </tr>
                            <tr>
                                <td class="title">학번</td>
                                <td class="content">{{ this.user.studentno }}</td>
                                <td class="title">학과</td>
                                <td class="content">{{ this.userinfo.major }}</td>
                                <td class="title">보호자 관계</td>
                                <td class="content">{{ this.userinfo.guardian_relation }}</td>
                            </tr>
                            <tr>
                                <td class="title">영문</td>
                                <td class="content">{{ this.userinfo.english_name }}</td>
                                <td class="title">학적상태</td>
                                <td class="content">{{ this.userinfo.student_status }}</td>
                                <td class="title">보호자 연락처</td>
                                <td class="content">{{ this.userinfo.guardian_phone }}</td>
                            </tr>
                            <tr>
                                <td class="title">한자</td>
                                <td class="content">{{ this.userinfo.chinese_name }}</td>
                                <td class="title">연락처</td>
                                <td class="content">{{ this.userinfo.phone }}</td>
                                <td class="title">자택 전화</td>
                                <td class="content">{{ this.userinfo.landline }}</td>
                            </tr>
                            <tr>
                                <td class="title">학년/성별</td>
                                <td class="content">{{ this.userinfo.grade }}학년 / {{ this.userinfo.gender }}</td>
                                <td class="title">이메일</td>
                                <td class="content">{{ this.user.email }}</td>
                            </tr>
                            <tr>
                                <td class="title">국적</td>
                                <td class="content">{{ this.userinfo.nationality }}</td>
                                <td class="title">주소</td>
                                <td class="content" style="font-size:12px">{{ this.userinfo.address }}</td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="division"></div>
                    <form action="" id="out_apply">
                        <div class="box">
                            <div class="title">퇴사 예정일</div>
                            <input type="date" v-model="res_date" data-placeholder="2022-07-25 (달력에서 선택하게 달력 삽입)"
                                required aria-required="true" className={styles.selectDay}>
                        </div>
                        <div class="box">
                            <div class="title">퇴사 사유</div>
                            <textarea v-model="res_reason" name="" id="" cols="30" rows="10"></textarea>
                        </div>
                        <input class="submit_btn" type="button" value="신청하기" @click="reserveResign()">
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
                                <td>8일 전까지<br />일전부터 지정입사일 이전까지</td>
                                <td>전액환불<br />입사관리비의 90%환불</td>
                                <td>*환불 시 일 수 산출의 1일 기준 금액 =<br />학기별납입금/학기수업일수 (백원미만절상)</td>
                            </tr>
                            <tr>
                                <td>지정 입사일 후</td>
                                <td>8주 이전</td>
                                <td>입사관리비의 30% 공제 후 일 수 산출하여 환불</td>
                                <td>최종퇴사일은 퇴사신고 또는 KEY(출입키<br />반납일을 기준으로 산출함)</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>9주 이후</td>
                                <td>환불하지 않음<br />
                                    질병 및 사고(진단서 첨부: 퇴사일 기준 15일 이내 발행), 취업<br />
                                    (실습)에 의한 퇴사 시 입사관리비의 30% 공제 후 일 수 산출하여<br />
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
                                    <input type="text" v-model="reason" placeholder="Ex). 본가 방문 (20자 이내)"
                                        maxlength="20">
                                </td>
                            </tr>
                            <tr>
                                <td class="title">신청 날짜</td>
                                <td class="content">
                                    <input type="date" v-model="date_sleepout"
                                        data-placeholder="2022-07-25 (달력에서 선택하게 달력 삽입)" required aria-required="true"
                                        className={styles.selectDay}>
                                </td>
                            </tr>
                        </table>
                        <input class="submit_btn" type="button" value="신청하기" @click="reserveSleepout()">
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
/* eslint-disable */
import SidebarCom from '@/components/SidebarCom.vue';
import PageTitle from "@/components/PageTitle.vue";
import UserService from '@/services/UserInfoDataService';
import UserDataService from '@/services/UserDataService';
import ApplyConsultDataService from "@/services/ApplyConsultDataService";
import ApplyStudyroomDataService from "@/services/ApplyStudyroomDataService";
import StudyroomScheduleGet from "@/services/StudyroomScheduleGet";
import ApplyJoinDataService from "@/services/ApplyJoinDataService";
import ApplyResignDataService from "@/services/ApplyResignDataService";
import ApplySleepoutDataService from "@/services/ApplySleepoutDataService";

export default {
    data() {
        return {
            title: "String",
            pageName: "신청 및 예약",
            // 사이드바 정보
            side: [
                { title: "상담 및 면담신청", path: "/reserve", active: true },
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
            seat: [
                { no: 1, status: true, isActive: true },
                { no: 2, status: true, isActive: false },
                { no: 3, status: true, isActive: false },
                { no: 4, status: true, isActive: false },
                { no: 5, status: true, isActive: false },
                { no: 6, status: true, isActive: false },
                { no: 7, status: true, isActive: false },
                { no: 8, status: true, isActive: false },
                { no: 9, status: true, isActive: false },
                { no: 10, status: true, isActive: false },
            ],
            mon_timeslot: [],
            tue_timeslot: [],
            wed_timeslot: [],
            thu_timeslot: [],
            fri_timeslot: [],
            consult_subject: "",
            consult_topic: "",
            timeslotList: [
                { time: "09:00~11:00", part:1, status: true, isActive: false },
                { time: "11:00~13:00", part:2, status: true, isActive: false },
                { time: "13:00~15:00", part:3, status: true, isActive: false },
                { time: "15:00~17:00", part:4, status: true, isActive: false },
                { time: "17:00~19:00", part:5, status: true, isActive: false },
                { time: "19:00~21:00", part:6, status: true, isActive: false },
                { time: "21:00~23:00", part:7, status: true, isActive: false },
            ],
            time_selected_cnt: '',
            time_selected_limit: 3,
            time_selected_list: [],
            seat_no: 1,
            userinfo: {},
            studyschedulelist: [],
            studyroom_status_list1: [],
            studyroom_status_list2: [],
            studyroom_status_list3: [],
            studyroom_status_list4: [],
            studyroom_status_list5: [],
            studyroom_status_list6: [],
            studyroom_status_list7: [],
            studyroom_status_list8: [],
            studyroom_status_list9: [],
            studyroom_status_list10: [],
            sleepout_item: [],
            // user_approved: false,
        }
    },
    components: { SidebarCom, PageTitle },
    created() {
        this.routeCheck()
        this.init()
    },
    methods: {
        routeCheck() {
            this.activeReset();
            this.side[2].semi = false;
            if (this.$route.name === "consult") {
                this.title = "신청 및 예약 > 상담 및 면담 신청";
                this.side[0].active = true;
            } else if (this.$route.name === "study") {
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
            } else if (this.$route.name === "sleep") {
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
        myFilterSeat(index) {
            for(const item in this.seat) {
                this.seat[item].isActive = false;
            }
            this.seat[index].isActive = !this.seat[index].isActive;
            this.seat_no = this.seat[index].no
        },
        myFilterTime(index) {
            if (this.timeslotList[index].isActive) {// 빼기
                this.time_selected_cnt--
                this.time_selected_list = this.time_selected_list.filter(item => item !== this.timeslotList[index].part) || []
                this.timeslotList[index].isActive = false;
            } else {                                // 더하기
                if(this.time_selected_cnt >= this.time_selected_limit) {
                    alert("하루 최대 이용 시간을 초과했습니다!")
                } else {
                    this.time_selected_cnt++
                    this.time_selected_list.push(this.timeslotList[index].part)
                    this.timeslotList[index].isActive = true;
                }
            }
        },
        alertMsg(){
            alert("입사 신청을 먼저 해주세요!")
        },
        // 상담 신청
        reserveConsult() {
            let day, data
            if (this.mon_timeslot) {
                day = "MON"
                data = {
                    studentNo: this.user.studentno,
                    dayOfWeek: day,
                    timeslots: this.mon_timeslot,
                    topic: this.consult_topic,
                    subject: this.consult_subject
                }
                ApplyConsultDataService.create(data)
            } if (this.tue_timeslot) {
                day = "TUE"
                data = {
                    studentNo: this.user.studentno,
                    dayOfWeek: day,
                    timeslots: this.tue_timeslot,
                    topic: this.consult_topic,
                    subject: this.consult_subject
                }
                ApplyConsultDataService.create(data)
            } if (this.wed_timeslot) {
                day = "WED"
                data = {
                    studentNo: this.user.studentno,
                    dayOfWeek: day,
                    timeslots: this.wed_timeslot,
                    topic: this.consult_topic,
                    subject: this.consult_subject
                }
                ApplyConsultDataService.create(data)
            } if (this.thu_timeslot) {
                day = "THU"
                data = {
                    studentNo: this.user.studentno,
                    dayOfWeek: day,
                    timeslots: this.thu_timeslot,
                    topic: this.consult_topic,
                    subject: this.consult_subject
                }
                ApplyConsultDataService.create(data)
            } if (this.fri_timeslot) {
                day = "FRI"
                data = {
                    studentNo: this.user.studentno,
                    dayOfWeek: day,
                    timeslots: this.fri_timeslot,
                    topic: this.consult_topic,
                    subject: this.consult_subject
                }
                ApplyConsultDataService.create(data)
            }
            alert("신청 완료했습니다.");
            location.reload(true);
        },
        // 스터디룸 신청
        reserveStudyroom() {
            let data = {
                studentNo: this.user.studentno,
                seatNo: this.seat_no,
                timeslots: this.time_selected_list
            }
            ApplyStudyroomDataService.create(data)
            alert("신청 완료했습니다.");
            location.reload(true);
        },
        // 입사 신청
        reserveJoin() {
            let data = {
                studentNo: this.user.studentno,
                english_name: this.english_name,
                chinese_name: this.chinese_name,
                grade: this.grade,
                gender: this.gender,
                nationality: this.nationality,
                department: this.department,
                major: this.major,
                student_status: this.student_status,
                phone: this.phone,
                address: this.address,
                guardian_name: this.guardian_name,
                guardian_relation: this.guardian_relation,
                guardian_phone: this.guardian_phone,
                landline: this.landline,
                hope_fac_1: this.hope_fac_1,
                hope_fac_2: this.hope_fac_2,
                hope_fac_3: this.hope_fac_3,
                hope_fac_4: this.hope_fac_4,
                hope_fac_5: this.hope_fac_5,
                hope_fac_6: this.hope_fac_6,
                period: this.period,
                single_yn: this.single_yn,
                pri_ent: this.pri_ent,
                fgn_mate: this.fgn_mate,
                accpm_ent: this.accpm_ent,
                par_mes_yn: this.par_mes_yn
            }
            if(this.hope_fac_1 == this.hope_fac_2 || this.hope_fac_1 == this.hope_fac_3 || this.hope_fac_1 == this.hope_fac_4 || this.hope_fac_1 == this.hope_fac_5
            || this.hope_fac_1 == this.hope_fac_6 || this.hope_fac_2 == this.hope_fac_3 || this.hope_fac_2 == this.hope_fac_4 || this.hope_fac_2 == this.hope_fac_5
            || this.hope_fac_2 == this.hope_fac_6 || this.hope_fac_3 == this.hope_fac_4 || this.hope_fac_3 == this.hope_fac_5 || this.hope_fac_3 == this.hope_fac_6
            || this.hope_fac_4 == this.hope_fac_5 || this.hope_fac_4 == this.hope_fac_6 || this.hope_fac_5 == this.hope_fac_6){
                alert("희망 시설이 중복되었습니다.")
            } else{
                ApplyJoinDataService.create(data)
                alert("신청 완료했습니다.");
                location.reload(true);
            }
        },
        // 퇴사 신청
        reserveResign() {
            let data = {
                studentNo: this.user.studentno,
                res_date: this.res_date,
                res_reason: this.res_reason
            }
            ApplyResignDataService.create(data)
            alert("신청 완료했습니다.");
            location.reload(true);
        },
        // 외박 신청
        reserveSleepout() {
            let sameCnt = 0, lengthCnt = 0

            //외박 신청한 날짜와 중복 여부
            while(this.sleepout_item.length>lengthCnt){
                if(this.sleepout_item[lengthCnt].outdate==this.date_sleepout){
                    sameCnt++
                }
                lengthCnt++
            }

            if(sameCnt>0){
                alert("이미 신청한 날짜입니다!")
            } else{
                let data = {
                    studentNo: this.user.studentno,
                    date_sleepout: this.date_sleepout,
                    reason: this.reason
                }
                ApplySleepoutDataService.create(data)
                alert("신청 완료했습니다.");
                location.reload(true);
            }
        },

        init(){
            //사용자 정보 가져오기
            UserService.getInfo(this.user.studentno).then(item => {
                let res = item.data
                let getinfo = {}

                getinfo.english_name = res.english_name
                getinfo.chinese_name = res.chinese_name
                getinfo.grade = res.grade
                getinfo.gender = res.gender
                getinfo.nationality = res.nationality
                getinfo.department = res.department
                getinfo.major = res.major
                getinfo.student_status = res.student_status
                getinfo.phone = res.phone
                getinfo.address = res.address
                getinfo.guardian_name = res.guardian_name
                getinfo.guardian_relation = res.guardian_relation
                getinfo.guardian_phone = res.guardian_phone
                getinfo.landline = res.landline
                getinfo.point = res.point
                getinfo.res_fac = res.res_fac
                getinfo.res_room = res.res_room

                this.userinfo = getinfo
            })

            //사용자 선택 좌석 개수 가져오기
            ApplyStudyroomDataService.getAll().then(item => {
                let res = item.data
                let list = []

                for (let i=0; i<res.length; i++) {
                    if(res[i].studentNo==this.user.studentno){
                        if(res[i].timeslot1){
                            list.push(res[i].timeslot1)
                        } if(res[i].timeslot2){
                            list.push(res[i].timeslot2)
                        } if(res[i].timeslot3){
                            list.push(res[i].timeslot3)
                        }
                    }
                }
                
                if(list.length>0){
                    this.time_selected_cnt = list.length
                } else {
                    this.time_selected_cnt = 0
                }
            })

            //현재 스터디룸 좌석 정보 가져오기
            StudyroomScheduleGet.getAll().then(item => {
                let res = item.data
                let list = []

                for (let i=0; i<res.length; i++) {
                    list.push({})
                    list[i].no = res[i].id
                    list[i].seat = res[i].seat
                    list[i].timeslot1_taken = res[i].timeslot1_taken
                    list[i].timeslot2_taken = res[i].timeslot2_taken
                    list[i].timeslot3_taken = res[i].timeslot3_taken
                    list[i].timeslot4_taken = res[i].timeslot4_taken
                    list[i].timeslot5_taken = res[i].timeslot5_taken
                    list[i].timeslot6_taken = res[i].timeslot6_taken
                    list[i].timeslot7_taken = res[i].timeslot7_taken
                    if(i==0){
                        this.studyroom_status_list1.push(res[i].timeslot1_taken)
                        this.studyroom_status_list1.push(res[i].timeslot2_taken)
                        this.studyroom_status_list1.push(res[i].timeslot3_taken)
                        this.studyroom_status_list1.push(res[i].timeslot4_taken)
                        this.studyroom_status_list1.push(res[i].timeslot5_taken)
                        this.studyroom_status_list1.push(res[i].timeslot6_taken)
                        this.studyroom_status_list1.push(res[i].timeslot7_taken)
                    } else if(i==1){
                        this.studyroom_status_list2.push(res[i].timeslot1_taken)
                        this.studyroom_status_list2.push(res[i].timeslot2_taken)
                        this.studyroom_status_list2.push(res[i].timeslot3_taken)
                        this.studyroom_status_list2.push(res[i].timeslot4_taken)
                        this.studyroom_status_list2.push(res[i].timeslot5_taken)
                        this.studyroom_status_list2.push(res[i].timeslot6_taken)
                        this.studyroom_status_list2.push(res[i].timeslot7_taken)
                    } else if(i==2){
                        this.studyroom_status_list3.push(res[i].timeslot1_taken)
                        this.studyroom_status_list3.push(res[i].timeslot2_taken)
                        this.studyroom_status_list3.push(res[i].timeslot3_taken)
                        this.studyroom_status_list3.push(res[i].timeslot4_taken)
                        this.studyroom_status_list3.push(res[i].timeslot5_taken)
                        this.studyroom_status_list3.push(res[i].timeslot6_taken)
                        this.studyroom_status_list3.push(res[i].timeslot7_taken)
                    } else if(i==3){
                        this.studyroom_status_list4.push(res[i].timeslot1_taken)
                        this.studyroom_status_list4.push(res[i].timeslot2_taken)
                        this.studyroom_status_list4.push(res[i].timeslot3_taken)
                        this.studyroom_status_list4.push(res[i].timeslot4_taken)
                        this.studyroom_status_list4.push(res[i].timeslot5_taken)
                        this.studyroom_status_list4.push(res[i].timeslot6_taken)
                        this.studyroom_status_list4.push(res[i].timeslot7_taken)
                    } else if(i==4){
                        this.studyroom_status_list5.push(res[i].timeslot1_taken)
                        this.studyroom_status_list5.push(res[i].timeslot2_taken)
                        this.studyroom_status_list5.push(res[i].timeslot3_taken)
                        this.studyroom_status_list5.push(res[i].timeslot4_taken)
                        this.studyroom_status_list5.push(res[i].timeslot5_taken)
                        this.studyroom_status_list5.push(res[i].timeslot6_taken)
                        this.studyroom_status_list5.push(res[i].timeslot7_taken)
                    } else if(i==5){
                        this.studyroom_status_list6.push(res[i].timeslot1_taken)
                        this.studyroom_status_list6.push(res[i].timeslot2_taken)
                        this.studyroom_status_list6.push(res[i].timeslot3_taken)
                        this.studyroom_status_list6.push(res[i].timeslot4_taken)
                        this.studyroom_status_list6.push(res[i].timeslot5_taken)
                        this.studyroom_status_list6.push(res[i].timeslot6_taken)
                        this.studyroom_status_list6.push(res[i].timeslot7_taken)
                    } else if(i==6){
                        this.studyroom_status_list7.push(res[i].timeslot1_taken)
                        this.studyroom_status_list7.push(res[i].timeslot2_taken)
                        this.studyroom_status_list7.push(res[i].timeslot3_taken)
                        this.studyroom_status_list7.push(res[i].timeslot4_taken)
                        this.studyroom_status_list7.push(res[i].timeslot5_taken)
                        this.studyroom_status_list7.push(res[i].timeslot6_taken)
                        this.studyroom_status_list7.push(res[i].timeslot7_taken)
                    } else if(i==7){
                        this.studyroom_status_list8.push(res[i].timeslot1_taken)
                        this.studyroom_status_list8.push(res[i].timeslot2_taken)
                        this.studyroom_status_list8.push(res[i].timeslot3_taken)
                        this.studyroom_status_list8.push(res[i].timeslot4_taken)
                        this.studyroom_status_list8.push(res[i].timeslot5_taken)
                        this.studyroom_status_list8.push(res[i].timeslot6_taken)
                        this.studyroom_status_list8.push(res[i].timeslot7_taken)
                    } else if(i==8){
                        this.studyroom_status_list9.push(res[i].timeslot1_taken)
                        this.studyroom_status_list9.push(res[i].timeslot2_taken)
                        this.studyroom_status_list9.push(res[i].timeslot3_taken)
                        this.studyroom_status_list9.push(res[i].timeslot4_taken)
                        this.studyroom_status_list9.push(res[i].timeslot5_taken)
                        this.studyroom_status_list9.push(res[i].timeslot6_taken)
                        this.studyroom_status_list9.push(res[i].timeslot7_taken)
                    } else {
                        this.studyroom_status_list10.push(res[i].timeslot1_taken)
                        this.studyroom_status_list10.push(res[i].timeslot2_taken)
                        this.studyroom_status_list10.push(res[i].timeslot3_taken)
                        this.studyroom_status_list10.push(res[i].timeslot4_taken)
                        this.studyroom_status_list10.push(res[i].timeslot5_taken)
                        this.studyroom_status_list10.push(res[i].timeslot6_taken)
                        this.studyroom_status_list10.push(res[i].timeslot7_taken)
                    }
                }
                this.studyschedulelist = list
            })

            UserService.getSleepout(this.user.studentno).then(sleepooutData => {
                let res = sleepooutData.data
                let list = []

                for (let i=0; i<res.length; i++) {
                    list.push({})
                    list[i].no = res[i].id
                    list[i].reason = res[i].reason
                    list[i].outdate = res[i].date_sleepout
                    list[i].approved = res[i].approved
                }
                this.sleepout_item = list
            })
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
                if (this.seat[item].isActive == true) {
                    no = this.seat[item].no
                }
            }
            return no
        },
        selectedTime(){
            let time = []
            for (const item in this.timeslotList) {
                if (this.timeslotList[item].isActive == true) {
                    time += this.timeslotList[item].part
                }
            }
            return time
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

                        td:first-child,
                        td:nth-child(3) {
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

                            .submit_btn {
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

                        tr,
                        td {
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
                                cursor: auto;
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

                        &.timeslotList {
                            background-color: #fff;
                            padding: 25px 20px;
                            margin: 10px 10px;

                            &.active {
                                background-color: #336EB4;
                                color: #fff;
                            }

                            &.disable {
                                border-color: #858585;
                                color: #fff;
                                background-color: #858585;
                                cursor: auto;
                            }
                        }

                        &.submit_btn {
                            display: block;
                            background-color: #336EB4;
                            color: #fff;
                            font-size: 14px;
                            padding: 16px 20px;
                            margin-top: 100px;
                            border: none;

                            &:hover {
                                cursor: pointer;
                            }
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

                                &:focus::-wedkit-input-placeholder {
                                    color: transparent;
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

                .submit_btn {
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

                            &:focus::-wedkit-input-placeholder {
                                color: transparent;
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

                    input[type=date] {
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

            .submit_btn {
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