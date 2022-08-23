Vue.createApp({

    data() {
        return {
            emailLogin: "",
            passwordLogin: "",
            firstName: "",
            lastName: ""
        }
    },

    created() {
    },

    methods: {
        login() {
            axios.post('/api/login', `email=${this.emailLogin}&password=${this.passwordLogin}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response =>
                    window.location.href = "http://localhost:8080/web/accounts.html",
                    console.log("login"))
                .catch(function (error) {
                    error.message
                })
        },

        logout() {
            axios.post('/api/logout')
                .then(response =>
                    window.location.href = "http://localhost:8080/web/index.html",
                    console.log('signed out!!!'))
                .catch(function (error) {
                    error.message
                })
        },

        register() {
            axios.post('/api/clients', `firstName=${this.firstName}&lastName=${this.lastName}&email=${this.emailLogin}&password=${this.passwordLogin}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => console.log('registered'))
                .then(() => this.login())
        }
    }
}).mount("#app")