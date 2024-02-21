const { createApp } = Vue

let app = createApp({
  data() {
    return {
      data: [],
      accounts: [],
      loans: [],
      accountNumber: [],
      selectedType: "",
      numAccounts: 0
    }
  },

  created() {
    this.loadData()
    this.formatBudget()
  },
  methods: {
    loadData() {
      axios.get("/api/clients/current")
        .then(response => {
          this.data = response.data
          this.accounts = this.data.accounts
          this.loans = response.data.clientLoans
          this.numAccounts = this.accounts.length
          console.log(this.data)
          console.log(this.accounts)
          console.log(this.loans)
        })
        .catch(error => console.log(error))
    },
    createAccount() {
      axios.post("/api/clients/current/accounts?typeAccount=" + this.selectedType)
        .then(response => {
          this.create = response.data;
          if (response.status.toString().startsWith("2")) {
            window.location.href = "/web/accounts.html"
            this.loadData();
          }
        })
        .catch(error => console.log(error));
    },
    deleteAccount(accountNumber) {
      axios.patch("/api/clients/current/accounts/delete?number=" + this.accountNumber)
        .then(response => {
          console.log(response)
          window.location.href = "/web/accounts.html"
        })
        .catch(error => console.log(error))
    },


    formatBudget(balance) {
      if (balance !== undefined && balance !== null) {
        return balance.toLocaleString("en-US", {
          style: "currency",
          currency: "ARS",
          minimumFractingDigits: 0,
        })
      }
    },

    deleteClient() {
      axios.delete("/api/clients/current")
        .then(response => {
          console.log(response)
        })
        .catch(error => console.log(error))
    },
    logout() {
      axios.post("/api/logout")
        .then(response => {
          console.log(response)
          if (response.status == 200) {
            window.location.href = "../index.html"
          }
        })
    },

    createAccountAlert() {
      Swal.fire({
        position: "top-center",
        icon: "success",
        title: "Successful Login",
        showConfirmButton: false,
      });
    },

  }
}).mount('#app')