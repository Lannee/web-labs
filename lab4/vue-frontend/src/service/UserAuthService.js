

class UserAuthService {
    constructor() {}

    register_user(user_name, email, password) {
        console.log("user_name: "+ user_name + " email: " + email + " password: " + password)
    }
}

export default new UserAuthService()