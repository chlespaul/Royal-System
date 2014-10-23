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
define([], function() {
    App.Model._HomologacionModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : '' ,  
		 'materiaExtranjera' : '' ,  
		 'equivalenciaCreditos' : '' ,  
		 'estadoId' : '' ,  
		 'materiauniandesId' : ''        },
        initialize: function() {
          var self = this;
          this.on('invalid',function(error){
                 Backbone.trigger(self.get('componentId') + '-' + 'error',{event: 'validation', message: error.validationError});
          });
        },
        getDisplay: function(name) {
			 if(name=='estadoId'){  
                 var value = App.Utils.getModelFromCache('estadoHomologacionComponent',this.get('estadoId'));
                 if(value) 
                 return value.get('name');
             }
			 if(name=='materiauniandesId'){  
                 var value = App.Utils.getModelFromCache('materiaUniandesComponent',this.get('materiauniandesId'));
                 if(value) 
                 return value.get('name');
             }
         return this.get(name);
        }
    });

    App.Model._HomologacionList = Backbone.PageableCollection.extend({
        model: App.Model._HomologacionModel,
        initialize: function() {
        },
		queryParams: {
		    currentPage: "page",
		    pageSize: "maxRecords"
		},
        parseState: function (resp, queryParams, state, options) {
          return {totalRecords: resp.totalRecords};
        },
        parseRecords: function (resp, options) {
          return resp.records;
        }
    });
    return App.Model._HomologacionModel;
});