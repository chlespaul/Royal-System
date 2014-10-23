define(['controller/selectionController', 'model/cacheModel', 'model/experienciaMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/experienciaComponent',
 'component/comentarioComponent', 'component/archivoComponent'],
 function(SelectionController, CacheModel, ExperienciaMasterModel, CRUDComponent, TabController, ExperienciaComponent,
 comentarioComponent, archivoComponent) {
    App.Component._ExperienciaMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('experienciaMaster');
            App.Model.ExperienciaMasterModel.prototype.urlRoot = this.configuration.context;
            this.componentId = App.Utils.randomInteger();
            
            this.masterComponent = new ExperienciaComponent();
            this.masterComponent.initialize();
            
            this.childComponents = [];
			
			this.initializeChildComponents();
            
            Backbone.on(this.masterComponent.componentId + '-post-experiencia-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-post-experiencia-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-pre-experiencia-list', function() {
                self.hideChilds();
            });
            Backbone.on('experiencia-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'experiencia-master-save', view: self, message: error});
            });
            Backbone.on(this.masterComponent.componentId + '-instead-experiencia-save', function(params) {
                self.model.set('experienciaEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }

				App.Utils.fillCacheList(
					'comentario',
					self.model,
					self.comentarioComponent.getDeletedRecords(),
					self.comentarioComponent.getUpdatedRecords(),
					self.comentarioComponent.getCreatedRecords()
				);

				App.Utils.fillCacheList(
					'archivo',
					self.model,
					self.archivoComponent.getDeletedRecords(),
					self.archivoComponent.getUpdatedRecords(),
					self.archivoComponent.getCreatedRecords()
				);

                self.model.save({}, {
                    success: function() {
                        Backbone.trigger(self.masterComponent.componentId + '-' + 'post-experiencia-save', {view: self, model : self.model});
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'experiencia-master-save', view: self, error: error});
                    }
                });
			    if (this.postInit) {
					this.postInit();
				}
            });
        },
        render: function(domElementId){
			if (domElementId) {
				var rootElementId = $("#"+domElementId);
				this.masterElement = this.componentId + "-master";
				this.tabsElement = this.componentId + "-tabs";

				rootElementId.append("<div id='" + this.masterElement + "'></div>");
				rootElementId.append("<div id='" + this.tabsElement + "'></div>");
			}
			this.masterComponent.render(this.masterElement);
		},
		initializeChildComponents: function () {
			this.tabModel = new App.Model.TabModel({tabs: [
                {label: "Comentario", name: "comentario", enable: true},
                {label: "Archivo", name: "archivo", enable: true}
			]});
			this.tabs = new TabController({model: this.tabModel});

			this.comentarioComponent = new comentarioComponent();
            this.comentarioComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.comentarioComponent);

			this.archivoComponent = new archivoComponent();
            this.archivoComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.archivoComponent);

            var self = this;
            
            this.configToolbar(this.comentarioComponent,true);
            Backbone.on(self.comentarioComponent.componentId + '-post-comentario-create', function(params) {
                params.view.currentModel.setCacheList(params.view.currentList);
            });
            
            this.configToolbar(this.archivoComponent,false);
            this.archivoComponent.disableEdit();

            Backbone.on(this.archivoComponent.componentId + '-toolbar-add', function() {
                var selection = new SelectionController({"componentId":"archivoComponent"});
                App.Utils.getComponentList('archivoComponent', function(componentName, model) {
                    if (model.models.length == 0) {
                        alert('There is no Archivos to select.');
                    } else {
                        selection.showSelectionList({list: model, name: 'name', title: 'Archivo List'});
                    }
                    ;
                });
            });

            Backbone.on('archivoComponent-post-selection', function(models) {
            	self.archivoComponent.addRecords(models);
            	self.archivoComponent.render();
            });

		},
        renderChilds: function(params) {
            var self = this;
            
            var options = {
                success: function() {
                	self.tabs.render(self.tabsElement);

					self.comentarioComponent.clearCache();
					self.comentarioComponent.setRecords(self.model.get('listcomentario'));
					self.comentarioComponent.render(self.tabs.getTabHtmlId('comentario'));

					self.archivoComponent.clearCache();
					self.archivoComponent.setRecords(self.model.get('listarchivo'));
					self.archivoComponent.render(self.tabs.getTabHtmlId('archivo'));

                    $('#'+self.tabsElement).show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'experiencia-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.ExperienciaMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.ExperienciaMasterModel();
                options.success();
            }


        },
        showMaster: function (flag) {
			if (typeof (flag) === "boolean") {
				if (flag) {
					$("#"+this.masterElement).show();
				} else {
					$("#"+this.masterElement).hide();
				}
			}
		},
        hideChilds: function() {
            $("#"+this.tabsElement).hide();
        },
		configToolbar: function(component, composite) {
		    component.removeGlobalAction('refresh');
			component.removeGlobalAction('print');
			component.removeGlobalAction('search');
			if (!composite) {
				component.removeGlobalAction('create');
				component.removeGlobalAction('save');
				component.removeGlobalAction('cancel');
				component.addGlobalAction({
					name: 'add',
					icon: 'glyphicon-send',
					displayName: 'Add',
					show: true
				}, function () {
					Backbone.trigger(component.componentId + '-toolbar-add');
				});
			}
        },
        getChilds: function(name){
			for (var idx in this.childComponents) {
				if (this.childComponents[idx].name === name) {
					return this.childComponents[idx].getRecords();
				}
			}
		},
		setChilds: function(childName,childData){
			for (var idx in this.childComponents) {
				if (this.childComponents[idx].name === childName) {
					this.childComponents[idx].setRecords(childData);
				}
			}
		},
		renderMaster: function(domElementId){
			this.masterComponent.render(domElementId);
		},
		renderChild: function(childName, domElementId){
			for (var idx in this.childComponents) {
				if (this.childComponents[idx].name === childName) {
					this.childComponents[idx].render(domElementId);
				}
			}
		}
    });

    return App.Component._ExperienciaMasterComponent;
});