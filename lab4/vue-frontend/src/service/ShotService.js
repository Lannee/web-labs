import axios from 'axios'

const SHOTS_API_ADD_URL = 'http://localhost:8080/api/shots/add'
const SHOTS_API_ALL_URL = 'http://localhost:8080/api/shots/all'
const SHOTS_API_CLEAR_URL = 'http://localhost:8080/api/shots/clear'

class ShotService {
    listeners
    constructor() {
        this.listeners = []
    }

    notifyListeners() {
        this.listeners.forEach(element => {
            element.updateResults()
            // console.log(element);
        });
    }

    addToListeners(val) {
        this.listeners.forEach(element => {
            element.addResult(val)
        });
    }

    clearListenersResults() {
        this.listeners.forEach(element => {
            element.clearResults()
        });
    }

    async shot(x, y, r) {
        const token = localStorage.getItem('token')
        const self = this

        if(token) {
            return axios.post(SHOTS_API_ADD_URL, {
                x: x,
                y: y,
                r: r,
                token: token
              })
              .then(function (response) {
                self.addToListeners(response.data)
                return response
              }).catch(function(err) {
                console.log("error: " + err)
                return err
              })
        } else {
            return "Invalid token"
        }
    }

    async getShots() {
        const token = localStorage.getItem('token')

        if(token) {
            return axios.post(SHOTS_API_ALL_URL, {
                token: token
              })
              .then(function (response) {
                return response
              }).catch(function(err) {
                err.response.data
              })
        } else {
            return "Invalid token"
        }
    }

    clear() {
        const token = localStorage.getItem('token')
        const self = this

        if(token) {
            return axios.post(SHOTS_API_CLEAR_URL, {
                token: token
              })
              .then(function (response) {
                self.clearListenersResults()
                return response
              }).catch(function(err) {
                return err.response.data
              })
        } else {
            return "Invalid token"
        }

    }

    addListener(listener) {
        this.listeners.push(listener)
    }
}

export default new ShotService()