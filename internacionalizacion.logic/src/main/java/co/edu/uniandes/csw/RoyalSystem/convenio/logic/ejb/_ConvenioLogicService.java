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


Source generated by CrudMaker version 1.0.0.201411201032

*/

package co.edu.uniandes.csw.RoyalSystem.convenio.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.RoyalSystem.convenio.logic.dto.ConvenioDTO;
import co.edu.uniandes.csw.RoyalSystem.convenio.logic.dto.ConvenioPageDTO;
import co.edu.uniandes.csw.RoyalSystem.convenio.logic.api._IConvenioLogicService;
import co.edu.uniandes.csw.RoyalSystem.convenio.persistence.api.IConvenioPersistence;

public abstract class _ConvenioLogicService implements _IConvenioLogicService {

	@Inject
	protected IConvenioPersistence persistance;

	public ConvenioDTO createConvenio(ConvenioDTO convenio){
		return persistance.createConvenio( convenio); 
    }

	public List<ConvenioDTO> getConvenios(){
		return persistance.getConvenios(); 
	}

	public ConvenioPageDTO getConvenios(Integer page, Integer maxRecords){
		return persistance.getConvenios(page, maxRecords); 
	}

	public ConvenioDTO getConvenio(Long id){
		return persistance.getConvenio(id); 
	}

	public void deleteConvenio(Long id){
	    persistance.deleteConvenio(id); 
	}

	public void updateConvenio(ConvenioDTO convenio){
	    persistance.updateConvenio(convenio); 
	}	
}