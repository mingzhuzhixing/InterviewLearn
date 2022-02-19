package com.v.mvp_module.view;

import com.v.mvp_module.bean.Girl;
import com.v.mvp_module.model.GirlModelImplV1;
import com.v.mvp_module.model.IGirlModel;

import java.util.List;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-05 15:34
 */
public class GirlPresenterV1 {
    /**
     * view
     */
    IGirlView mGirlView;


    /**
     * model
     */
    IGirlModel mGirlModel=new GirlModelImplV1();

    public GirlPresenterV1(IGirlView girlView){
        this.mGirlView=girlView;
    }

    /**
     * 绑定model 和 view
     */
    public void fetch(){
        mGirlView.showLoading();

        if(mGirlModel!=null){

            mGirlModel.loadGirl(new IGirlModel.GirlOnLoadListener() {
                @Override
                public void onComplete(List<Girl> girls) {
                    //给view显示
                    mGirlView.showGirls(girls);
                }
            });
        }
    }

//    public void setGirlModel(IGirlModel girlModel) {
//        mGirlModel = girlModel;
//    }
}
