package com.v.interviewlearn.mvp.view;

import com.v.interviewlearn.mvp.bean.Girl;
import com.v.interviewlearn.mvp.model.GirlModelImplV2;
import com.v.interviewlearn.mvp.model.IGirlModel;

import java.util.List;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-05 15:34
 */
public class GirlPresenterV3 extends BasePresenter<IGirlView>{
    /**
     * model
     */
    IGirlModel mGirlModel=new GirlModelImplV2();

    /**
     * 绑定model 和 view
     */
    public void fetch(){
        getView().showLoading();

        if(mGirlModel!=null){

            mGirlModel.loadGirl(new IGirlModel.GirlOnLoadListener() {
                @Override
                public void onComplete(List<Girl> girls) {
                    //给view显示
                    getView().showGirls(girls);
                }
            });
        }
    }
}
