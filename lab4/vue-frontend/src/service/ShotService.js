class ShotService {
    constructor() {}

    shots: []

    shot(x, y, r) {
        console.log("x: "+ x + " y: " + y + " r: " + r)
    }
}

export default new ShotService()