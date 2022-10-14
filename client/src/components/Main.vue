<template>
<div id="home">

    <img src="../pics/makeup_shop.jpg" width="44%" height="30vh" style="margin-left:340px">
	<router-link to="/produse">
		<img src="../assets/log-in.png"  style="position:absolute;margin-top:250px; margin-left:-850px"></router-link
		>
	</div>
</template>

<script>
export default {
  name: 'Main',
  props: {
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
