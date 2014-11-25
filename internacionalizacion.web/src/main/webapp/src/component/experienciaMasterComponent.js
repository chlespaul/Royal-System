define(['component/_experienciaMasterComponent'],function(_ExperienciaMasterComponent) {
    App.Component.ExperienciaMasterComponent = _ExperienciaMasterComponent.extend({
		postInit: function(){
             this.toolbarComponent.hideButton('print');
			//Escribir en este servicio las instrucciones que desea ejecutar al inicializar el componente
		}
    });

    return App.Component.ExperienciaMasterComponent;
});