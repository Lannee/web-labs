import axios from 'axios'

const SHOTS_API_BASE_URL = 'http://localhost:8080/'

class ShotService {
    constructor(shots) {
        this.shots = shots
    }

    shot(x, y, r) {
        axios.post(SHOTS_API_BASE_URL, {
            x: x,
            y: y,
            r: r
          })
          .then(function (response) {
            console.log(response);
          });

        // console.log("x: "+ x + " y: " + y + " r: " + r)
    }

    getShots() {
        return this.shots
    }
}

export default new ShotService()