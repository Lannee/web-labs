import axios from 'axios'

const USER_LOGIN_BASE_URL = 'http://localhost:8080/api/auth/login'
const USER_REGISTER_BASE_URL = 'http://localhost:8080/api/auth/register'
const USER_TOKEN_VERIFY_BASE_URL = 'http://localhost:8080/api/auth/token_verify'
 


class UserAuthService {
    constructor() {}

    async login_user(user_name, password) {
        return await axios.post(USER_LOGIN_BASE_URL, {
            login: user_name,
            password: password
        }).then(function(res){
            if(res.data.token != undefined) {
                localStorage.setItem("token", res.data.token)
                return "Success"
            } 
            return res.data
        }).catch(function(err) {
            return err.response.data
        })
    }

    async register_user(user_name, email, password) {
        return await axios.post(USER_REGISTER_BASE_URL, {
            login: user_name,
            email: email,
            password: password
        }).then(function(res) {
            return res.data
        }).catch(function(res) {
            return res.response.data
        })
    }

    async getLoginByToken(token) {
        return axios.post(USER_TOKEN_VERIFY_BASE_URL, {
            token: token
        }).then(function(res) {
            return res
        }).catch(function(res) {
            return res.response.data
        })
    }
}

export default new UserAuthService()