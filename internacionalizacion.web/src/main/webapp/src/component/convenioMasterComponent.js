define(['component/_convenioMasterComponent'],function(_ConvenioMasterComponent) {
    App.Component.ConvenioMasterComponent = _ConvenioMasterComponent.extend({
		postInit: function(){
             this.toolbarComponent.hideButton('print');
			//Escribir en este servicio las instrucciones que desea ejecutar al inicializar el componente
		}
    });

    return App.Component.ConvenioMasterComponent;
});