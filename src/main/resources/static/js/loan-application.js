const { createApp } = Vue
const app = createApp({
    data() {
        return {
            data: [],
            selectLoan: "-1",
            amount: "",
            accountDest: "",
            SelectPayment: "-1",
            paymentsFilter: "-1",

        }
    },
    created() {
        this.loadData()
    },
    methods: {
        loadData() {
            axios.get("/api/loans")
                .then(response => {
                    this.data = response.data
                    console.log(this.data)
                })
                .catch(error => {
                    console.log(error)
                })
        },
        Payments() {
            const filter = this.data.find(loan => loan.id  == this.selectLoan)
            this.paymentsFilter = filter.payments
        },
        createLoans() {
            const body = {
                "id": this.selectLoan,
                "amount": this.amount,
                "payments": this.SelectPayment,
                "accountDestination": this.accountDest
            }
            axios.post("/api/loans", body)
                .then(response => {
                    this.data = response.data
                    window.location.href="/web/accounts.html"
                    console.log(this.data)
                })
                .catch(error => {
                    console.log(error)
                })

                
        },
        logout(){
            axios.post("/api/logout")
                .then(response => {
                    console.log(response)
                    if (response.status == 200) {
                        window.location.href = "../index.html"
                    }
                })
            }
    }
}).mount('#app')