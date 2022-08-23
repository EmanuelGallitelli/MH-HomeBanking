Vue.createApp({

    data() {
      return {
        jsonClientes: [],
        nombre: "",
        apellido: "",
        email: ""
      }
    },
  
    created() {
      axios.get("http://localhost:8080/api/clients")
        .then(datos => {
          this.jsonClientes = datos.data
        })
    },
  
    methods: {
      enviarCliente(){
        axios.post("http://localhost:8080/api/clients", {
          firstName: this.nombre,
          lastName: this.apellido,
          emailClient: this.email
        })
        .catch(error => console.log(error))
      },
  
      eliminarFila(id){
        axios.delete(id)
      }
    }
  }).mount("#app")