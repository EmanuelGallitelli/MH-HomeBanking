Vue.createApp({
    data() {
        return {
            jsonClient: [],
            loans: [],
            arrayLoans: [],
            paymentsCuota: [],
            typeLoan: 0,
            amount: 0,
            amountPayment: 0,
            paymentsAvailable: 0,
            description: "",
            destinationAccount: "",
            accounts: ""
        }
    },

    created() {
        axios.get('/api/loans')
            .then(datos => {
                this.loans = datos.data
                console.log(this.loans)
            }),

            axios.get("http://localhost:8080/api/clients/current")
                .then(datos => {
                    this.jsonClient = datos.data
                    this.accounts = this.jsonClient.accounts
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

        createdLoan() {
            let applyLoan = {
                id: this.typeLoan,
                amount: this.amount,
                payments: this.paymentsAvailable,
                destinationAccountNumber: this.destinationAccount
            }

            axios.post('/api/loans', applyLoan)
                .then(response => console.log('loan done'))
                .then(response => window.location.href = "http://localhost:8080/web/accounts.html")
                .catch(function (error) {
                    error.message
                })
        },

        filtro() {
            this.arrayLoans = this.loans.filter((prestamo) => prestamo.id == this.typeLoan)

            this.paymentsCuota = this.arrayLoans[0].payments

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
                .then(() => this.createdLoan())
        }
    }

}).mount("#app")