Vue.createApp({
    data() {
        return {
            jsonClient: [],
            accounts: [],
            amount: 0,
            originAccountNumber: "",
            destinationAccountNumber: "",
            description: ""
        }
    },

    created() {
        axios.get("http://localhost:8080/api/clients/current")
            .then(datos => {
                this.jsonClient = datos.data
                this.accounts = datos.data.accounts
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

        createdTransactions() {
            axios.post('/api/transactions', `amount=${this.amount}&originAccountNumber=${this.originAccountNumber}&destinationAccountNumber=${this.destinationAccountNumber}&description=${this.description}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => console.log('transaction done'))
                .then(response => window.location.href = "http://localhost:8080/web/accounts.html")
                .catch(function (error) {
                    error.message
                })
        },

        cartel() {
            Swal.fire({
                title: 'Are you sure?',
                text: "Do you want to make this transfer?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, do it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire(
                        'Done!',
                        'You have sent a transfer.',
                        'success'
                    )
                }
            })
                .then(() => this.createdTransactions())
        }
    }

}).mount("#app")