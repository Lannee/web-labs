<template>
    <div class="modalWindow" id="loginWindow">
        <h1 class="subtitle">Вход в аккаунт</h1>
        <p class="separated">Имя пользователя</p>
        <div class="inputWrapper">
            <input type="text">
        </div>
        <p class="separated">Пароль</p>
        <div class="inputWrapper">
            <input type="password">
        </div>
        <div>Register</div>
        <div class="button" style="margin-top: 20px;">
            <p>Вход</p>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            isHidenForm: true
        }
    },
    methods: {
        showLoginForm() {
            this.isHidenForm = false;
        },
        hideLoginForm() {
            this.isHidenForm = true;
        }
    }
}
</script>

<style>

</style>