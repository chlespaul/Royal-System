/* ========================================================================
 * Copyright 2014 RoyalSystem
 *
 * Licensed under the MIT, The MIT License (MIT)
 * Copyright (c) 2014 RoyalSystem

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 * ========================================================================


Source generated by CrudMaker version 1.0.0.201410152247

*/
define(['model/_intercambioModel'], function() {
    App.Model.IntercambioModel = App.Model._IntercambioModel.extend({

 		validate: function(attrs,options){
            var validationMessage = "";
            if(!attrs.name){
                validationMessage = "El nombre no puede ser vacio";
            }
            
            if(!attrs.fechaCreacion){
                validationMessage = "La fecha no puede estar vacia";
            }
            if(!attrs.estudianteId){
                validationMessage = "El estudiante no puede ser vacio.";
            }
            if(parseInt(attrs.estadoId)!=2){
                validationMessage = "Es necesario seleccionar estado en espera";
            }
             if(!attrs.convenioId){
                validationMessage = "Es necesario seleccionar un convenio";
            }
            
            
            
            if(validationMessage.length>0){
               return validationMessage;
            }
        },
         validarEstado:function(attrs)
        {
            
             var validationMessage = "";
            
             if(attrs.estadoId.length==0){
                validationMessage = "Tienes que Seleccionar un estado.";
            }
            
            if(validationMessage.length>0){
                alert(validationMessage)
               return validationMessage;
            }
        }

    });

    App.Model.IntercambioList = App.Model._IntercambioList.extend({
        model: App.Model.IntercambioModel
    });

    return  App.Model.IntercambioModel;

});