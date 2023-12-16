<template>
    <div id="modalWindowBack">
        <div class="modalWindow convex" id="loggedUserInfo">
        </div>    
        <div class="modalWindow convex" id="loginWindow" v-if="!showRegister">
            <div class="closeButton" @click="$emit('close')"></div>
            <h1 class="subtitle">Sing In</h1>
            <p class="separated">Login</p>
            <div class="inputWrapper">
                <input type="text" class="concave" v-model="sing_in_user_name">
            </div>
            <p class="separated">Password</p>
            <div class="inputWrapper">
                <input type="password" class="concave" v-model="sing_in_password">
            </div>
            <div id="registerLink">Not registered yet? <a @click="showRegister = true">register</a></div>
            <div class="button convex" style="margin-top: 20px;" @click="sing_in">
                <p>Sing In</p>
            </div>
        </div>
        <div class="modalWindow convex" id="signupWindow" v-if="showRegister">
            <div class="closeButton" @click="$emit('close')"></div>
            <h1 class="subtitle">Registration</h1>
            <p class="separated">Login</p>
            <div class="inputWrapper">
                <input type="text" class="concave" v-model="register_user_name">
            </div>
            <p class="separated">E-Mail</p>
            <div class="inputWrapper">
                <input type="text" id="mailInput" class="concave" v-model="register_email">
            </div>
            <p class="separated">Password</p>
            <div class="inputWrapper">
                <input type="password" class="concave" v-model="register_password">
            </div>
            <div class="button convex" style="margin-top: 20px;" @click="register">
                <p>Register</p>
            </div>
        </div>
    </div>
</template>

<script>
import userAuthService from '@/service/UserAuthService'

export default {
    data() {
        return {
            isLogged: false,
            showRegister: false,

            register_user_name: "",
            register_email: "",
            register_password: "",

            sing_in_user_name: "",
            sing_in_password: ""
        }
    },
    methods: {
        register() {
            userAuthService.register_user(this.register_user_name, this.register_email, this.register_password)
        },

        sing_in() {

        }
    }
}
</script>

<style scoped>

a {
    cursor: pointer;
}

.modalWindow::before {
    position: relative;

    width: 100%;
    height: 100%;

    margin: auto;

    z-index: 101;
    background-color: #868E9C;
}

#registerLink {
    margin-top: 10px;
}

#registerLink a {
    font-size: 18px;
    font-style: italic;
}

input:focus,
select:focus,
textarea:focus,
button:focus {
    outline: none;
}

.modalWindow {
    transition: height 0ms 0ms, opacity 300ms 0ms, transform 300ms 0ms, filter 300ms 50ms;
    opacity: 1;
    filter: blur(0px);
    transform: scale(1) translate(-50%, -50%);
    z-index: 100;
    position: absolute;
    display: flex;
    flex-direction: column;
    top: 50%;
    left: 50%;
    padding: 30px;
    overflow: hidden;
    max-height: 800px;
    border-radius: 20px;
}

.subtitle{
    margin: 0;
}

.modalWindow h2{
    margin: 0px;
}

.modalWindow .subtitle {
    margin-right: 100px;
}

.modalWindow .imageWrapper {
    opacity: .9;
}

.closeButton {
    height: 30px;
    width: 30px;
    position: absolute;
    top: 30px;
    right: 30px;
    background-image: url('../static/images/close.png');
    filter: invert();
    background-size: cover;
    cursor: pointer;
    z-index: 10;
    transition: .2s;
}

.inputWrapper{
    height: 50px;
    width: 350px;
    border-radius: 14px;
    overflow: hidden;
    transition: .2s;
}

.invalid:has(input:focus){
    border: 1px solid rgb(255, 51, 51);
}

.inputWrapper input{
    height: 100%;
    width: 100%;
    border: none;
    /* font-size: 16px; */
    padding: 0px 20px;
}

.separated{
    margin-top: 20px;
    margin-bottom: 8px;
}

.button{
    border-radius: 18px;
    /* background-color: rgb(238, 238, 238); */
    padding: 14px 20px;
    width: fit-content;
    transition: .2s;
    transform: scale(1);
}

.button:active{
    transform: scale(0.96);
}

.button p{
    margin: 0;
    width: fit-content;
    font-size: 18px;
}

</style>