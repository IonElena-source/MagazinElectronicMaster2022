<template>
    <div class="Produse">
        <SideBar></SideBar>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">    
        <div id="modalProdus" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
                <span class="close" @click="closeModal">&times;</span>
                
                

                    <label for="lname">Detalii produs</label>

                    <input type="text" id="numeProdus" name="lastname" placeholder="Nume produs" style="margin-bottom:20px; ">
                    <input type="number" id="pretProdus" name="lastname" placeholder="Pret produs" style="margin-bottom:20px;">
                    <input type="text" id="cantitateProdus" name="lastname" placeholder="Cantitate produs" style="margin-bottom:20px;">

                    <input type="file" id="myFile" name="filename">
                   
                
            </div>

        </div>
        <div class="w3-row w3-border">
            <div class="w3-quarter w3-container" v-for="(produs,index) in produse" v-bind:key="produs.id">
                <v-card class="mx-auto" max-width="374" style="margin-top:20px" :id="'produs' + index" max-height:120px>
                    <v-card-text >
                        <h4 class="title" style="text-align:center">{{produs.nume}} </h4>
                        <div class="text--primary ">
                            <img :src="require(`../pics/${produs.photo}`)" width="110px" style="margin-left:60px; cursor: pointer;">
                        
                        </div>
                        <h4 class="title" style="margin-left:70px">{{produs.pret}} lei  / {{produs.cantitate}} </h4>
                       Nota:  <input  :v-model="calculateMedieNote(produs)" v-bind:placeholder="calculateMedieNote(produs)" readonly/>

                    </v-card-text>
                    <v-card-actions  >
                        <input type="number" :id="produs.codProdus" name="quantity" min="1" max="5" placeholder="Nota" style="width: 120px;">
                        <button @click="trimiteNota(produs)">
                            <img src="../assets/nota.png" width="30px" style="margin-left:10px; cursor: pointer;">
                        </button>
                        <hr>
                         

                        <button id="increment" @click="increment(produs)">+</button>  
                        <h2 :class="produs.codProdus">1</h2>
                        <button @click="decrement(produs)" id="decrement">-</button>         
                        
                        </v-card-actions>
                        <button id="btnAddInCos" @click="addInCos(produs)">Adauga in cos</button>      

                </v-card>
            </div>

        </div>
    </div>
</template>

<script>
import SideBar from "./SideBar";
import axios from "axios"




export default {
    name: 'ProduseClient',
    components: { SideBar },
    data() {
        return {
            produse: [{ id: 1, nume: "Ion", prenume: "Elena", varsta: 70, photo: "" },
            { id: 2, nume: "Samsung Galaxy A20", varsta: 20, photo: "" },
            { id: 12, nume: "Ion", prenume: "Elena", varsta: 20, photo: "" },
            { id: 22, nume: "Ion", prenume: "Elena", varsta: 20, photo: "" },
            { id: 32, nume: "Ion", prenume: "Elena", varsta: 20 },
            { id: 42, nume: "Ion", prenume: "Elena", varsta: 20 },
            { id: 52, nume: "Ion", prenume: "Elena", varsta: 20 },
            { id: 62, nume: "Ion", prenume: "Maria", varsta: 20 },
            { id: 72, nume: "Ion", prenume: "Vasilica", varsta: 60 },
            {
                id: 3,
                nume: "Ion",
                prenume: "Elena",
                varsta: 20
            }
            ],
            revealProdusrId: -1,
            reveal: false,
            rating:0,
            boundRating:0,
            produseSelectate:[]
         
        };
    },
    methods: {
        // getImgUrl(produs) {
        //     return require(  produs.photo)
        // },
        setRating(produs,rating){
            this.rating = rating
            console.log(rating)
            console.log(this.boundRating)
        },
        displayInfoProdus(produs) {
            this.revealProdusId = produs.id

        },
        openModalAddProdus() {
            var modal = document.getElementById("modalProdus");
            modal.style.display = "block";
        },
        closeModal() {
            var modal = document.getElementById("modalProdus");
            modal.style.display = "none";
        },
        openModalProgramari() {
            var modal = document.getElementById("modalProgramari");
            modal.style.display = "block";
        },
        closeModalProgramari() {
            var modal = document.getElementById("modalProgramari");
            modal.style.display = "none";
        },
        back() {
            this.$router.push("/home")
        },
        calculateMedieNote( produs){
            if(produs.note==null) return "Nicio nota"
            if(produs.note.length ==0 ) return "Nicio nota"
                let sum=0;
                for(let i=0;i<produs.note.length;i++){
                    sum+=produs.note[i]
                }
                return sum/produs.note.length
        },
        deleteProdus(produs){
            axios.delete(`http://localhost:8000/produse/${produs.codProdus}`).then(() => {
				alert("Produs sters");
				axios.get("http://localhost:8000/produse").then((response) => {

					this.produse = response.data;
                    for(let i=0;i<this.produse.length;i++){
                            this.produse[i].data=1
                    }

				
				});
            })
        },
        trimiteNota(produs){
            let nota = document.getElementById(produs.codProdus).value
            
            axios.patch(`http://localhost:8000/produse/note/${produs.codProdus}/${nota}`, parseInt(nota)).then(() => {
                document.getElementById(produs.codProdus).value =0
				alert("Nota trimisa");
				axios.get("http://localhost:8000/produse").then((response) => {
					this.produse = response.data;
                    for(let i=0;i<this.produse.length;i++){
                            this.produse[i].data=1
                    }
				});
            })
        },  
        adaugaProdus(){
            let numeProdus = document.getElementById("numeProdus").value
            let pretProdus = document.getElementById("pretProdus").value

            let cantitateProdus = document.getElementById("cantitateProdus").value
        
            console.log(document.getElementById("myFile").value)
            let srcPhoto= document.getElementById("myFile").value.split("\\")
            console.log(srcPhoto)
            let pozaSrc = srcPhoto[srcPhoto.length-1]
            let produs = {
                nume: numeProdus,
                pret: pretProdus,
                cantitate: cantitateProdus,
                photo: pozaSrc
            }
            axios.post("http://localhost:8000/produse", produs).then(() => {
				alert("Produs adaugat");
				axios.get("http://localhost:8000/produse").then((response) => {
					this.produse = response.data;
                    for(let i=0;i<this.produse.length;i++){
                            this.produse[i].data=1
                    }
				});
            })
             

        },
        increment(produs) {
        produs.data++;
         document.getElementsByClassName(produs.codProdus)[0].innerText =produs.data
},
        decrement(produs) {
        if(produs.data==0) return;
        produs.data--;
         document.getElementsByClassName(produs.codProdus)[0].innerText = produs.data
        },
        addInCos(produs){
        this.produseSelectate.push(produs)
         localStorage.setItem("produseSelectate",JSON.stringify(this.produseSelectate))
  
        }
    },
   

    mounted() {

        axios.get("http://localhost:8000/produse").then((response) => {
            console.log("fne")
            console.log(response.data)
            this.produse = response.data;
            for(let i=0;i<this.produse.length;i++){
                            this.produse[i].data=1
                    }

        });
        // regasire interviuri

    },
    created() {
        if (localStorage.getItem("tokenAngajat") === null) {
            this.$router.push("/angajat/auth");
        }
    },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->

<style scoped>
#btnAdd {
    margin-top: 30px;
    margin-left: -920px;

}
.w3-border {
    border: 1px solid #ffffff!important;
}
.mx-auto{
    height: 492px;
}
.v-card > *:last-child:not(.v-btn):not(.v-chip):not(.v-avatar) {
    border-bottom-left-radius: inherit;
    border-bottom-right-radius: inherit;
    margin-top: 10px;
   
}
.card-container {
    width: 100%;
    height: auto;
    margin: auto 0;
    display: flex;
    justify-content: center;
}
.card-container ul {
    text-align: center;
    margin: auto 0;

}

.card-container li {
    list-style-type: none;
    padding: .5em .5em .5em .5em;
    text-align: left;
}

.card {
    transition: 0.2s;
    overflow: hidden;
    width: 7em;
    word-wrap: break-word;
}

.card-image {
    box-shadow: 1 1 rgb(206, 192, 192);
    transition: .3s;
    background-color: #C33;
    margin: auto 0;
    text-align: center;
}

.title {
    text: center;
    font-size: 18px;

    justify-content: center;
}

.card-image:hover {
    transform: scale(1.25, 1.25);
    transform-origin: 50% 50%;
    box-shadow: 2 2 #0f0;
}

.v-card--reveal {
    bottom: 0;
    opacity: 1 !important;
    position: absolute;
    width: 100%;
}

.modal {
    display: none;
    position: fixed;
    z-index: 1;
    padding-top: 100px;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgb(0, 0, 0);
    background-color: rgba(0, 0, 0, 0.4);

}

.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid rgb(255, 252, 252);
    width: 50%;
}

.close {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}

input[type=text],
select {
    width: 100%;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=submit] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type=submit]:hover {
    background-color: #45a049;
}

form.example input[type=text] {
    padding: 10px;
    border: 1px solid grey;
    float: left;
    width: 80%;
    background: #f1f1f1;
}

form.example button {
    float: left;
    width: 20%;
    padding: 8px;
    background: #2196F3;
    color: white;
    font-size: 17px;
    border: 1px solid grey;
    border-left: none;
    cursor: pointer;
}

form.example button:hover {
    background: #0b7dda;
}

form.example::after {
    content: "";
    clear: both;
    display: table;
}
.container {
    justify-content: center;
    align-items: center;
    display: flex;
    height: 100%;
    text-align: center;
}
  
/*apply css properties to button tag*/
  
#increment, #decrement {
    width: 30px;
    height: 30px;
    font-size: 20px;
    margin-left: 20px;
    background-color: green;
    color: honeydew;
}
  
/*apply hover effect to button tag*/
  
#increment:hover {
    background-color: greenyellow;
    color: grey;
}

#decrement:hover {
    background-color: greenyellow;
    color: grey;
}
  
/*apply css properties to h2 tag*/
#btnAddInCos{

  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 15px 12px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;

}

</style>
