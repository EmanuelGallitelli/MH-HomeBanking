Vue.createApp({
    
	data() {
		return {
			jsonClient: [],
			accounts: [],
			loans: [],
			tableLoan: null
		}
	},

	created() {
		axios.get("http://localhost:8080/api/clients/current")
			.then(datos => {
				this.jsonClient = datos.data
				this.accounts = this.jsonClient.accounts.sort((a, b) => b.id - a.id)
				this.loans = this.jsonClient.loans


			})
	},

	methods: {
		logout() {
            axios.post('/api/logout')
                .then(response =>
                    window.location.href = "http://localhost:8080/web/index.html",
                    console.log('signed out!!!'))
                .catch(function (error) {
                    error.message
                })
        },

		createAccount(){
			axios.post('/api/clients/current/accounts', {headers: { 'content-type': 'application/x-www-form-urlencoded' }})
                .then(response => window.location.reload())
		}
	}
}).mount("#app")