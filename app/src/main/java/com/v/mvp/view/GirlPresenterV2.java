package com.v.mvp.view;

import com.v.mvp.bean.Girl;
import com.v.mvp.model.GirlModelImplV2;
import com.v.mvp.model.IGirlModel;

import java.util.List;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-05 15:34
 */
public class GirlPresenterV2 {
    /**
     * view
     */
    IGirlView mGirlView;


    /**
     * model
     */
    IGirlModel mGirlModel=new GirlModelImplV2();

    public GirlPresenterV2(IGirlView girlView){
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
