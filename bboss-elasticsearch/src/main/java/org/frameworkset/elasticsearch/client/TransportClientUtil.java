package org.frameworkset.elasticsearch.client;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ResponseHandler;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.client.Client;
import org.frameworkset.elasticsearch.ElasticSearchEventSerializer;
import org.frameworkset.elasticsearch.ElasticSearchException;
import org.frameworkset.elasticsearch.EventDeliveryException;
import org.frameworkset.elasticsearch.IndexNameBuilder;
import org.frameworkset.elasticsearch.entity.AggHit;
import org.frameworkset.elasticsearch.entity.ESAggDatas;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.frameworkset.elasticsearch.entity.ESIndice;
import org.frameworkset.elasticsearch.entity.IndexField;
import org.frameworkset.elasticsearch.entity.MapRestResponse;
import org.frameworkset.elasticsearch.entity.MapSearchHit;
import org.frameworkset.elasticsearch.entity.RestResponse;
import org.frameworkset.elasticsearch.event.Event;
import org.frameworkset.elasticsearch.handler.ESAggBucketHandle;
import org.frameworkset.elasticsearch.serial.ESTypeReferences;

public class TransportClientUtil  implements EventClientUtil{
	private ElasticSearchTransportClient client;
	 private BulkRequestBuilder bulkRequestBuilder;
	 private IndexNameBuilder indexNameBuilder;
	public TransportClientUtil(EventElasticSearchClient client,IndexNameBuilder indexNameBuilder) {
		this.client = (ElasticSearchTransportClient)client;
		this.indexNameBuilder = indexNameBuilder;
	}
	/**
	 * 查询模板
	 * @param template
	 * @return
	 * @throws ElasticSearchException
	 */
	public   String getTempate(String template) throws ElasticSearchException {
		return null;
	}
	
	/**
	 * 查询所有模板
	 * @param template
	 * @return
	 * @throws ElasticSearchException
	 */
	public   String getTempate() throws ElasticSearchException {
		return null;
	}
	public void addEvent(Event event,ElasticSearchEventSerializer  elasticSearchEventSerializer) throws ElasticSearchException {
		init();

	    
	    try {
			IndexRequestBuilder indexRequestBuilder = client.createIndexRequest(
			           indexNameBuilder , event,  elasticSearchEventSerializer);
//	    if (indexRequestBuilderFactory == null) {
//	      XContentBuilder bytesStream = null;
//	      try {
//	        bytesStream = client.getContentBuilder(event);
//	        indexRequestBuilder = client
//	                .prepareIndex(indexNameBuilder.getIndexName(event), indexType)
//	                .setSource(bytesStream );
//	      }
//	      finally {
//	        if(bytesStream != null){
////	          bytesStream.cl
//	        }
//	      }
//
//	    } else {
//	      indexRequestBuilder = client.createIndexRequest(
//	           indexNameBuilder.getIndexPrefix(event), indexType, event);
//	    }
//
//	    if (ttlMs > 0) {
//	      indexRequestBuilder.setTTL(ttlMs);
//	    }
			bulkRequestBuilder.add(indexRequestBuilder);
		} catch (IOException e) {
			throw new ElasticSearchException(e);
		}
	  }

 
	  public Object execute() throws ElasticSearchException {
	    try {
	      BulkResponse bulkResponse = bulkRequestBuilder.execute().actionGet();
	      if (bulkResponse.hasFailures()) {
	        throw new EventDeliveryException(bulkResponse.buildFailureMessage());
	      }
	      return bulkResponse;
	    } finally {
	      
	    }
	  }
	  private void init(){
		  if (bulkRequestBuilder == null) {
		      bulkRequestBuilder = client.prepareBulk();
		    }
	  }
	 
	
	public String deleteDocuments(String indexName, String indexType, String... ids) throws ElasticSearchException {
		init();
		for(int i = 0; i < ids.length; i ++){
			try {
				bulkRequestBuilder.add(client.deleteIndex(  indexName,   indexType,   ids[i]));
			} catch (Exception e) {
				throw new ElasticSearchException(e);
			}
		}
		return null;
		
	}

 
	public void updateIndexs(Event event,ElasticSearchEventSerializer  elasticSearchEventSerializer)throws ElasticSearchException{
		try {
			UpdateRequestBuilder indexRequestBuilder = client.updateIndexRequest(
					 event,  elasticSearchEventSerializer);
//	    if (indexRequestBuilderFactory == null) {
//	      XContentBuilder bytesStream = null;
//	      try {
//	        bytesStream = client.getContentBuilder(event);
//	        indexRequestBuilder = client
//	                .prepareIndex(indexNameBuilder.getIndexName(event), indexType)
//	                .setSource(bytesStream );
//	      }
//	      finally {
//	        if(bytesStream != null){
////	          bytesStream.cl
//	        }
//	      }
//
//	    } else {
//	      indexRequestBuilder = client.createIndexRequest(
//	           indexNameBuilder.getIndexPrefix(event), indexType, event);
//	    }
//
//	    if (ttlMs > 0) {
//	      indexRequestBuilder.setTTL(ttlMs);
//	    }
			bulkRequestBuilder.add(indexRequestBuilder);
		} catch (IOException e) {
			throw new ElasticSearchException(e);
		}
	}
	 
	public Client getClient() {
		// TODO Auto-generated method stub
		return this.client.getClient();
	}
	@Override
	public <T> T getIndexMapping(String index, ResponseHandler<T> responseHandler) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T getIndexMapping(String index, boolean pretty, ResponseHandler<T> responseHandler)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<IndexField> getIndexMappingFields(String index, String indexType) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String addDocuments(String indexName, String indexType, String addTemplate, List<?> beans)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String updateDocuments(String indexName, String indexType, String updateTemplate, List<?> beans)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String addDocument(String indexName, String indexType, String addTemplate, Object bean)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDocument(String indexName, String indexType, String documentId) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDocument(String indexName, String indexType, String documentId, Map<String, Object> options)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T getDocument(String indexName, String indexType, String documentId, Class<T> beanType)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T getDocument(String indexName, String indexType, String documentId, Map<String, Object> options,
			Class<T> beanType) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MapSearchHit getDocumentHit(String indexName, String indexType, String documentId,
			Map<String, Object> options) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MapSearchHit getDocumentHit(String indexName, String indexType, String documentId)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String addDateDocument(String indexName, String indexType, String addTemplate, Object bean)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String addDateDocuments(String indexName, String indexType, String addTemplate, List<?> beans)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String deleteDocument(String indexName, String indexType, String id) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object executeRequest(String path, String entity) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object executeRequest(String path) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String executeHttp(String path, String action) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String executeHttp(String path, String entity, String action) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String delete(String path, String string) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getIndexMapping(String index) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getIndexMapping(String index, boolean pretty) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String executeRequest(String path, String templateName, Map params) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String executeRequest(String path, String templateName, Object params) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T executeRequest(String path, String entity, ResponseHandler<T> responseHandler)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T executeRequest(String path, String templateName, Map params, ResponseHandler<T> responseHandler)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T executeRequest(String path, String templateName, Object params, ResponseHandler<T> responseHandler)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MapRestResponse search(String path, String templateName, Map params) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MapRestResponse search(String path, String templateName, Object params) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MapRestResponse search(String path, String entity) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, Object> searchMap(String path, String templateName, Map params) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, Object> searchMap(String path, String templateName, Object params)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, Object> searchMap(String path, String entity) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getIndice(String index) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String dropIndice(String index) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String updateIndiceMapping(String action, String indexMapping) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String createIndiceMapping(String indexName, String indexMapping) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String updateIndiceMapping(String action, String templateName, Object parameter)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String createIndiceMapping(String indexName, String templateName, Object parameter)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String updateIndiceMapping(String action, String templateName, Map parameter) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String createIndiceMapping(String indexName, String templateName, Map parameter)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ESIndice> getIndexes() throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String refreshIndexInterval(String indexName, int interval) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String refreshIndexInterval(String indexName, String indexType, int interval) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String refreshIndexInterval(int interval, boolean preserveExisting) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String refreshIndexInterval(int interval) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RestResponse search(String path, String templateName, Map params, Class<?> type)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RestResponse search(String path, String templateName, Object params, Class<?> type)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RestResponse search(String path, String entity, Class<?> type) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RestResponse search(String path, String templateName, Map params, ESTypeReferences type)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RestResponse search(String path, String templateName, Object params, ESTypeReferences type)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RestResponse search(String path, String entity, ESTypeReferences type) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> ESDatas<T> searchList(String path, String templateName, Map params, Class<T> type)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> ESDatas<T> searchList(String path, String templateName, Object params, Class<T> type)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> ESDatas<T> searchList(String path, String entity, Class<T> type) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T searchObject(String path, String templateName, Map params, Class<T> type)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T searchObject(String path, String templateName, Object params, Class<T> type)
			throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T searchObject(String path, String entity, Class<T> type) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T extends AggHit> ESAggDatas<T> searchAgg(String path, String entity, Map params, Class<T> type,
			String aggs, String stats, ESAggBucketHandle<T> aggBucketHandle) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T extends AggHit> ESAggDatas<T> searchAgg(String path, String entity, Object params, Class<T> type,
			String aggs, String stats, ESAggBucketHandle<T> aggBucketHandle) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T extends AggHit> ESAggDatas<T> searchAgg(String path, String entity, Class<T> type, String aggs,
			String stats, ESAggBucketHandle<T> aggBucketHandle) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T extends AggHit> ESAggDatas<T> searchAgg(String path, String entity, Map params, Class<T> type,
			String aggs, String stats) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T extends AggHit> ESAggDatas<T> searchAgg(String path, String entity, Object params, Class<T> type,
			String aggs, String stats) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T extends AggHit> ESAggDatas<T> searchAgg(String path, String entity, Class<T> type, String aggs,
			String stats) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String createTempate(String template, String entity) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String createTempate(String template, String templateName, Object params) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String createTempate(String template, String templateName, Map params) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String deleteTempate(String template) throws ElasticSearchException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	  
}
