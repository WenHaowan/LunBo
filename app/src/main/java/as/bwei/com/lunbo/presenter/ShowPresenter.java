package as.bwei.com.lunbo.presenter;

import as.bwei.com.lunbo.model.ShowModel;
import as.bwei.com.lunbo.view.ShowView;

/**
 * Created by HP on 2018/10/17.
 */

public class ShowPresenter {
    private ShowModel showModel;
    private ShowView showView;

    public ShowPresenter(ShowView showView) {
        this.showView = showView;
        showModel = new ShowModel();
    }

    public void show(){
        showModel.show(new ShowModel.ShowCallback() {
            @Override
            public void failure(String msg) {
                showView.failure(msg);
            }

            @Override
            public void success(String msg) {
                showView.success(msg);
            }
        });
    }
}
