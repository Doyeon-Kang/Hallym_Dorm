<template>
    <div class="join_container">
        <div class="join">
            <div class="joinbox">
                <div class="logo" @click="this.$router.push('/')">
                    <img src="@/assets/logo.png" alt="" />
                    <img src="@/assets/univ.png" alt="" />
                    <div class="univ">| Dormitory</div>
                </div>
                <div class="jointitle">회원가입</div>
                <div class="join_name">
                    <div class="join_text">이름</div>
                    <input type="text" v-model="name" placeholder="이름을 입력해주세요.">
                </div>
                <div class="join_id">
                    <div class="join_text">아이디</div>
                    <input type="text" v-model="id" placeholder="아이디를 입력해주세요.">
                    <div class="joinguide">※아이디는 한림대학교 학번과 동일하게 입력.</div>
                </div>
                <div class="join_pw">
                    <div class="join_text">비밀번호</div>
                    <input type="password" v-model="pwd" placeholder="비밀번호를 입력해주세요.">
                    <div class="joinguide">※비밀번호는 8자리 이상.</div>
                </div>
                <div class="join_pw_check">
                    <div class="join_text">비밀번호 확인</div>
                    <input type="password" v-model="pwd_check" placeholder="비밀번호를 다시 한 번 입력해주세요.">
                </div>
                <div class="join_email">
                    <div class="join_text">이메일</div>
                    <input type="text" v-model="email" placeholder="이메일을 입력해주세요.">
                </div>
                <div class="joinbtnbox joinbtn">회원가입</div>
                <div class="loginbtn" @click="this.$router.push('login')">◀ 로그인 페이지</div>
                <div class="homebtn" @click="this.$router.push('/')">홈페이지 ▶</div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data () {
            return {
                user: {
                    username: '',
                    name: '',
                    password: '',
                    email: ''
                },
                successful: false,
                loading: false,
                message: '',
            }
        },
        computed: {
            loggedIn() {
                return this.$store.state.auth.status.loggedIn
            },
        },
        mounted() {
            if (this.loggedIn) {
                this.$router.push("/mypage")
            }
        },
        methods: {
            handleRegister(user) {
                this.message = ''
                this.successful = false
                this.loading = true

                this.$store.dispatch("auth/register", user).then(
                    (data) => {
                        this.message = data.message
                        this.successful = true
                        this.loading = false
                    },
                    (error) => {
                        this.message =
                            (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                            error.message ||
                            error.toString()
                        this.successful = false
                        this.loading = false
                    }
                );
            },
        }
    }
</script>
  
<style scoped lang="less">
.join_container {
    width: 1080px;
    display: flex;

    .join {
        width: 58%;
        margin: 30px auto;
        box-shadow: 0px 0px 10px #bababa;
        color: #222222;

        .joinbox {
            width: 64%;
            margin: 0 auto;
            padding: 80px 0;

            .logo {
                width: 47%;
                margin: 0 auto;
                margin-bottom: 10px;

                img {
                    width: 32%;
                }

                .univ {
                    font-size: 12px;
                    font-weight: bold;
                    line-height: 40px;
                    float: right;
                }
            }

            .jointitle {
                clear: both;
                color: #336EB4;
                text-align: center;
                font-size: 40px;
                font-weight: bold;
                margin-bottom: 50px;
            }

            .join_name,
            .join_id,
            .join_pw,
            .join_pw_check,
            .join_email {
                color: #00B6AD;
                font-size: 17px;
                font-weight: bold;
                margin-bottom: 20px;
            }

            .join_id,
            .join_pw {
                margin-bottom: 40px;
            }

            .join_text {
                margin-bottom: 5px;
            }

            input {
                width: 100%;
                border: none;
                border-bottom: 2px solid #00B6AD;
                margin-bottom: 3px;
                padding: 10px 0;
                font-size: 14px;
            }

            .joinguide {
                color: #DD6464;
                font-size: 13px;
                float: left;
            }

            .joinbtnbox {
                clear: both;
                width: 100%;
                font-size: 18px;
                font-weight: bold;
                text-align: center;
                line-height: 50px;
            }

            .logo:hover,
            .joinbtnbox:hover,
            .loginbtn:hover,
            .homebtn:hover {
                cursor: pointer;
            }

            .joinbtn {
                background-color: #00B6AD;
                color: #FFFFFF;
                margin-top: 50px;
            }

            .loginbtn,
            .homebtn {
                color: #858585;
                font-weight: bold;
                margin-top: 10px;
                font-size: 13px;
            }

            .loginbtn {
                float: left;
            }

            .homebtn {
                float: right;
            }
        }
    }
}
</style>