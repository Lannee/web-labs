<template>
  <div class="content convex row">
        <CanvasComponent :radius="r_val" />
        <div id="form_div">
            <div id="input_form">
                <div class="input convex" id="x_checkbox"  style="display: block">
                    <div class="row" style="margin-bottom: 20px;">
                        <div class="input_text">Choose X:</div>
                    </div>
                    <div class="buttons" id="r_select">
                        <div>
                            <input type="checkbox" class="r_check" v-model="x_m5">
                            <div>-5</div>
                        </div>
                        <div>
                            <input type="checkbox" class="r_check" v-model="x_m4">
                            <div>-4</div>
                        </div>
                        <div>
                            <input type="checkbox" class="r_check" v-model="x_m3">
                            <div>-3</div>
                        </div>
                        <div>
                            <input type="checkbox" class="r_check" v-model="x_m2">
                            <div>-2</div>
                        </div>
                        <div>
                            <input type="checkbox" class="r_check" v-model="x_m1">
                            <div>-1</div>
                        </div>
                        <div>
                            <input type="checkbox" class="r_check" v-model="x_0">
                            <div>0</div>
                        </div>
                        <div>
                            <input type="checkbox" class="r_check" v-model="x_1">
                            <div>1</div>
                        </div>
                        <div>
                            <input type="checkbox" class="r_check" v-model="x_2">
                            <div>2</div>
                        </div>
                        <div>
                            <input type="checkbox" class="r_check" v-model="x_3">
                            <div>3</div>
                        </div>
                    </div>
                </div>
                <div class="input convex row" style="display: flex; justify-content: flex-start">
                    <div class="input_text" style="margin-right: 10px;">Enter Y:</div>
                    <input type="text" class="concave" placeholder="-3 ... 3" v-model="y_val">
                </div>
                <div class="input convex" style="display: block">
                    <div class="row" style="margin-bottom: 20px;">
                        <div class="input_text">Choose R:</div>
                    </div>
                    <div class="buttons" id="r_select">
                        <div>
                            <input type="checkbox" class="r_check" v-model="r_1">
                            <div>1</div>
                        </div>
                        <div>
                            <input type="checkbox" class="r_check" v-model="r_2">
                            <div>2</div>
                        </div>
                        <div>
                            <input type="checkbox" class="r_check" v-model="r_3">
                            <div>3</div>
                        </div>
                        <div>
                            <input type="checkbox" class="r_check" v-model="r_4">
                            <div>4</div>
                        </div>
                        <div>
                            <input type="checkbox" class="r_check" v-model="r_5">
                            <div>5</div>
                        </div>
                    </div>
                </div>
                <div id="submit_row">
                    <div style="width: fit-content">
                        <button class="convex" id="submit_button" @click="sendShot">Submit</button>
                        <div v-show="showTip" id="tip">{{ tipMessage }}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import CanvasComponent from './Canvas.vue'

import shotService from '@/service/ShotService'
import userAuthService from '@/service/UserAuthService'

import * as formValid from '@/validators/formValidator'
import router from '@/router'

export default {
    name: "MainForm",
    components: {
        CanvasComponent
    },
    data() {
        return {
            x_m5: false,
            x_m4: false,
            x_m3: false,
            x_m2: false,
            x_m1: false,
            x_0: true,
            x_1: false,
            x_2: false,
            x_3: false,

            r_1: true,
            r_2: false,
            r_3: false,
            r_4: false,
            r_5: false,

            x_val: 0,
            y_val: undefined,
            r_val: 1,

            showTip: false,
            tipMessage: ''
        }
    },
    methods: {
        updateXCheckboxes(variable, value) {
            if(value) {
                this.x_m5 = this.x_m4 = this.x_m3 = this.x_m2 = this.x_m1 = this.x_0 = this.x_1 = this.x_2 = this.x_3 = false;
                switch(variable) {
                    case 0: this.x_m5 = true; this.x_val = -5; break;
                    case 1: this.x_m4 = true; this.x_val = -4; break;
                    case 2: this.x_m3 = true; this.x_val = -3; break;
                    case 3: this.x_m2 = true; this.x_val = -2; break;
                    case 4: this.x_m1 = true; this.x_val = -1; break;
                    case 5: this.x_0 = true; this.x_val = 0; break;
                    case 6: this.x_1 = true; this.x_val = 1; break;
                    case 7: this.x_2 = true; this.x_val = 2; break;
                    case 8: this.x_3 = true; this.x_val = 3; break;
                }
            }
        },
        updateRCheckboxes(variable, value) {
            if(value) {
                this.r_1 = this.r_2 = this.r_3 = this.r_4 = this.r_5 = false;
                switch(variable) {
                    case 0: this.r_1 = true; this.r_val = 1; break;
                    case 1: this.r_2 = true; this.r_val = 2; break;
                    case 2: this.r_3 = true; this.r_val = 3; break;
                    case 3: this.r_4 = true; this.r_val = 4; break;
                    case 4: this.r_5 = true; this.r_val = 5; break;
                }
            }
        },
        validateX() {
            if(formValid.isXNotvalid(this.x_val)) {

                this.doShowTip("Invalid value for X")
                return false
            }
            this.hideTip()
            return true;
        },
        validateY() {
            if(formValid.isYNotvalid(this.y_val)) {
                this.doShowTip("Invalid value for Y")
                return false
            }
            this.hideTip()
            return true
        },
        validateR() {
            if(formValid.isRNotvalid(this.r_val)) {

                this.doShowTip("Invalid value for R")
                return false
            }
            this.hideTip()
            return true
        },
        validateForm() {
            this.checkToken()
            return this.validateX() && this.validateY() && this.validateR()
        },
        doShowTip(message) {
            this.showTip = true
            this.tipMessage = message
        },
        hideTip() {
            this.showTip = false
        },
        sendShot() {
            this.checkToken()
            if(this.validateForm()) {
                shotService.shot(this.x_val, parseFloat(parseFloat(this.y_val).toFixed(4)), this.r_val).then(res => {
                    if(res == 'Invalid token!') {
                        router.push('/')
                    } else if(res.data == undefined) {
                        this.doShowTip(res)
                    }
                })
            }    
        },

        checkToken() {
            const token = localStorage.getItem('token')

            if(token != undefined) {
                userAuthService.getLoginByToken(token).then(res => {
                    if(res.data) {
                        this.shown_name = res.data
                        this.isLogged = true
                    } else {
                        this.doShowTip(res)
                        router.push('/')
                    }
                })
            } else {
                router.push('/')
            }
        }
    },
    watch: {
        x_m5: function(val) {this.updateXCheckboxes(0, val)},
        x_m4: function(val) {this.updateXCheckboxes(1, val)},
        x_m3: function(val) {this.updateXCheckboxes(2, val)},
        x_m2: function(val) {this.updateXCheckboxes(3, val)},
        x_m1: function(val) {this.updateXCheckboxes(4, val)},
        x_0: function(val) {this.updateXCheckboxes(5, val)},
        x_1: function(val) {this.updateXCheckboxes(6, val)},
        x_2: function(val) {this.updateXCheckboxes(7, val)},
        x_3: function(val) {this.updateXCheckboxes(8, val)},

        r_1: function(val) {this.updateRCheckboxes(0, val)},
        r_2: function(val) {this.updateRCheckboxes(1, val)},
        r_3: function(val) {this.updateRCheckboxes(2, val)},
        r_4: function(val) {this.updateRCheckboxes(3, val)},
        r_5: function(val) {this.updateRCheckboxes(4, val)},

        x_val: function() {this.validateX()},
        y_val: function() {this.validateY()},
        r_val: function() {this.validateR()}
    }
}
</script>

<style>

@media screen and (max-width: 1050px) {
    .content {
        width: 60%;
        display: inline;
        justify-content: initial;
        align-content: space-around;
    }

    #form_div {
        width: 80%;
        margin: auto;
    }
}

@media screen and (min-width: 1050px) {
    .content {
        width: 60%;
        justify-content: space-around;
    }
}



#r_select, #x_select {
    display: flex;
    justify-content: space-around;
}

#submit_button {
    border: none;
    border-radius: 10px;
    padding: 14px 20px;
    transition: .2s;
    transform: scale(1);

    margin-bottom: 10px;

    cursor: pointer;
}

#submit_button:active {
    transform: scale(0.92);
}



</style>