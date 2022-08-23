Vue.createApp({

    data() {
        return {
            jsonClient: [],
            account: [],
            accounts: [],
            transactions: []

        }
    },

    created() {
        const urlParams = new URLSearchParams(window.location.search);
        const id = urlParams.get('id');
        axios.get("http://localhost:8080/api/accounts/" + `${id}`)
            .then(datos => {
                this.account = datos.data
                this.transactions = this.account.transactions.sort((a, b) => new Intl.Collator().compare(b.date, a.date))
                console.log(this.transactions)

                axios.get("http://localhost:8080/api/clients/current")
                    .then(datos => {
                        this.jsonClient = datos.data
                        this.accounts = this.jsonClient.accounts.sort((a, b) => b.id - a.id)
                    })
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
        }
    }
}).mount("#app")