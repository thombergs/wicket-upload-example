package org.wickedsource;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {

	private static final String TARGET_PATH = "C:\\temp";

	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);
		add(new UploadForm("uploadForm"));
	}

	private class UploadForm extends Form<Void> {

		private List<FileUpload> uploads;

		public UploadForm(String id) {
			super(id);
			FileUploadField uploadField = new FileUploadField("uploadField",
					new PropertyModel<List<FileUpload>>(this, "uploads"));
			add(uploadField);
			add(new FeedbackPanel("feedback"));
		}

		@Override
		protected void onSubmit() {
			try {
				String filePath = TARGET_PATH + "/"
						+ uploads.get(0).getClientFileName();
				uploads.get(0).writeTo(new File(filePath));
				info(String.format(
						"Upload successful. The file has been stored in %s",
						filePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
