<template>
  <!-- <div id="register_from" class="convex">
    <input id="register_username" class="concave" type="text" placeholder="Enter username" v-model="user_name">
    <input id="register_email" class="concave" type="email" placeholder="Enter email" v-model="email">
    <input id="register_password" class="concave" type="password" placeholder="Enter password" v-model="password">
    <button class="convex" @click="register">Submit</button>
  </div> -->
  <div class="modalWindow closedModalWindow" id="signupWindow">
        <h1 class="subtitle">Регистрация</h1>
        <p class="separated">Имя пользователя</p>
        <div class="inputWrapper">
            <input type="text">
        </div>
        <p class="separated">E-Mail</p>
        <div class="inputWrapper">
            <input type="text" id="mailInput">
        </div>
        <p class="separated">Пароль</p>
        <div class="inputWrapper">
            <input type="password">
        </div>
        <div class="button" style="margin-top: 20px;">
            <p>Регистрация</p>
        </div>
    </div>
</template>

<script>
import userAuthService from '@/service/UserAuthService'

export default {
    name: "RegisterFormComponent",
    data() {
        return {
            user_name: "",
            email: "",
            password: ""
        }
    },
    methods: {
        register() {
            userAuthService.register_user(this.user_name, this.email, this.password)
        }
    }
}
</script>

<style>
</style>