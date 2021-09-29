package com.example.motivation

class Frase(val frase: String, val categoria: Int)


class Mock {

    private val frase: List<Frase> = listOf(

        Frase("Não sabendo que era impossivel, foi la e fez. ", 2) ,
        Frase("Você não é derrotado quando perde, você é derrotado quando desiste. ", 2) ,
        Frase("Quando está mais escuro, vemos mais estrelas. ", 1) ,
        Frase("Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente. ", 1) ,
        Frase("Não pare quando estiver cansado, pare quando tiver terminado.  ", 1) ,
        Frase("O que você pode fazer agora que tem o maior impacto sobre o seu sucesso. ", 2) ,
        Frase("A melhor maneira de prever o futuro é inventa-lo.  ", 1) ,
        Frase("Você perde todas as chances que você não aproveita ", 3) ,
        Frase("Fracasso é o condimento que da sabor ao sucesso.  ", 3) ,
        Frase("Enquanto não estivermos comprometidos, haverá hesitação. ", 3) ,
        Frase("Se você não sabe onde quer ir, qualquer caminho serve. ", 3) ,
        Frase("Se você acredita, faz toda a diferença. ", 2) ,
        Frase("Riscos devem ser corridos, porque o maior perigo é não arriscar nada. ", 1)

        )

    fun getFrase(filtro: Int): String{

        if(filtro == 1){

            val random = Math.random();

            if(random < 0.2){

                return frase[2].frase

            }

            if(random > 0.2 && random < 0.4){

                return frase[3].frase

            }

            if(random > 0.4 && random < 0.6){

                return frase[4].frase

            }

            if(random > 0.6 && random < 0.8){

                return frase[6].frase

            }

            if(random > 0.8){

                return frase[12].frase

            }



        }

        if(filtro == 2){


            val random = Math.random();

            if(random < 0.25){

                return frase[0].frase

            }

            if(random > 0.25 && random < 0.5){

                return frase[1].frase

            }

            if(random > 0.5 && random < 0.75){

                return frase[5].frase

            }

            if(random > 0.75){

                return frase[11].frase

            }




        }


        if(filtro == 3){


            val random = Math.random();

            if(random < 0.25){

                return frase[7].frase

            }

            if(random > 0.25 && random < 0.5){

                return frase[8].frase

            }

            if(random > 0.5 && random < 0.75){

                return frase[9].frase

            }

            if(random > 0.75){

                return frase[10].frase

            }


        }

        return getFrase(filtro)
    }


}