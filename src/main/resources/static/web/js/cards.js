Vue.createApp({
    
	data() {
		return {
			jsonClient: [],
			cards: [],
			CardColor: "",
			CardType: ""
		}
	},

	created() {
		axios.get("http://localhost:8080/api/clients/current")
			.then(datos => {
				this.jsonClient = datos.data
				this.cards = this.jsonClient.cards
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

		addCards(){
			axios.post('/api/clients/current/cards', `type=${this.CardType}&color=${this.CardColor}`, {headers: { 'content-type': 'application/x-www-form-urlencoded' }})
			.then(response => {
				console.log("tarjeta creada")
				setTimeout(function(){
					window.location.href = "http://localhost:8080/web/cards.html"
				},500)
			})
		}
	}
}).mount("#app")