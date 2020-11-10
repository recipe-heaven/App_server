package no.twct.recipeheaven.response;

public class DataResponse extends AbstractResponse {

	public class Data {
		private Object data;

		public Data(Object data) {
			this.data = data;
		}

		public Object getData() {
			return this.data;
		}

	}

	private Data response;

	public DataResponse() {
		response = new Data("");
	}

	public DataResponse(Object data) {
		response = new Data(data);
	}

	public Data getResponse() {
		return this.response;
	}
}
