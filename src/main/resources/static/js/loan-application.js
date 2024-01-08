const { createApp } = Vue
const app = createApp({
    data() {
        return {
            data: [],
            selectLoan: "1",
            amount: "",
            accountDest: "",
            payments: "1",
            paymentsFilter: "1",

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
                    this.payments = response.data
                    console.log("hola", this.payments)

                    console.log(this.data)
                })
                .catch(error => {
                    console.log(error)
                })
        },
        Payments() {
            this.paymentsFilter = this.data.filter(loan => { return this.selectLoan == loan.id })[0]
        },
        createLoans() {
            const body = {
                "id": this.selectLoan,
                "amount": this.amount,
                "payments": this.payments,
                "accountDestination": this.accountDest
            }
            axios.post("/api/loans", body)
                .then(response => {
                    this.data = response.data
                    console.log(this.data)
                })
                .catch(error => {
                    console.log(error)
                })
        },
    }
}).mount('#app')