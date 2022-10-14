<template>
<div class="Home">
    <SideBar> sidebar</SideBar>
    <div class="contentm">
        <div class="container">
        <h3 class="p-3 text-center">Produse selectate pentru cumparaturi </h3>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Nume produs</th>
                    <th>Cantitate</th>
                    <th>Pret</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="produs in this.produse" :key="produs.codProdus">
                    <td>{{produs.nume}} </td>
                    <td>{{produs.data}} </td>
                    <td>{{produs.total}} </td>
                </tr>
            </tbody>
        </table>
        <p><button class="w3-button w3-block w3-black">Finalizeaza comanda</button></p>
    </div>    
        
    </div>

</div>
</template>

<script>
import SideBar from "./SideBar";
export default {
    name: 'Cos',
    components: { SideBar },
    props: {},
    data() {
        return {
            produse: [],
            revealProdusrId: -1,
            reveal: false
        };
    },
    mounted() {
    let produse = JSON.parse('[' + localStorage.getItem("produseSelectate") + ']');
    this.produse = produse[0];
    for(let i = 0;i<this.produse.length;i++){
        this.produse[i].total=this.produse[i].data*this.produse[i].pret
    }
    console.log(this.produse)
   }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->

<style scoped>

</style>
