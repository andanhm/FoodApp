package app.rk.food.view.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import app.rk.food.R;
import app.rk.food.utils.Utility;
import app.rk.food.view.activity.MainActivity;
import app.rk.food.view.adapter.FoodAdapter;
import app.rk.food.model.FoodData;
import app.rk.food.utils.Log;

/**
 *
 */
public class FoodFragment extends Fragment{
    private RecyclerView recyclerViewFood;
    private SwipeRefreshLayout swipeRefreshLayoutFoodSwipe;
    private FoodAdapter foodAdapter;
    private List<FoodData> mFoodDataList;
    private ProgressDialog mProgressDialog;
    private Activity mActivity;
    private FrameLayout frameLayoutCheckout;
    private String TAG=getClass().getSimpleName();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mFoodDataList =new ArrayList<>();
        mProgressDialog = new ProgressDialog(mActivity);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.progress_loading));
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.client_food_layout,null);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewFood = (RecyclerView) view.findViewById(R.id.recyclerViewFood);
        swipeRefreshLayoutFoodSwipe = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayoutFoodSwipe);
        frameLayoutCheckout=(FrameLayout)view.findViewById(R.id.frameLayoutCheckout);
        frameLayoutCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction mFragmentTransaction=getFragmentManager().beginTransaction();
                if (MainActivity.mCartItemList.size()==0) {
                    mFragmentTransaction.replace(R.id.containerView, new EmptyCartFragment(), TAG);
                } else {
                    mFragmentTransaction.replace(R.id.containerView, new FoodCartFragment(), TAG);
                }
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();

            }
        });

        setFoodAdapter();
        swipeRefreshLayoutFoodSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    swipeRefreshLayoutFoodSwipe.setRefreshing(false);
                } catch (Exception e) {
                    Log.printStackTrace(e);
                }
            }
        });
    }

    public void setFoodAdapter() {
        try {
            mFoodDataList = getFoodDataList();
            LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerViewFood.setLayoutManager(layoutManager);
            foodAdapter = new FoodAdapter(mActivity, mFoodDataList);
            recyclerViewFood.setAdapter(foodAdapter);
            swipeRefreshLayoutFoodSwipe.setRefreshing(false);
        } catch (Exception e) {
            Log.printStackTrace(e);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setFoodAdapter();
        Log.d(TAG, "onResume: ");
    }

    private List<FoodData> getFoodDataList(){
        List<FoodData> foodDataList=new ArrayList<>();
        FoodData mFoodOne=new FoodData();
        mFoodOne.setFoodId("1");
        mFoodOne.setFoodName("Dosa");
        mFoodOne.setFoodDescription("Dosa will be available till 12 AM");
        mFoodOne.setFoodPrice(40.00);
        mFoodOne.setFoodQuantity(1);
        mFoodOne.setFoodImgURL("http://www.namasteplaza.com/media/wysiwyg/Dosa/Masala-Dosa.jpg");

        FoodData mFoodTwo=new FoodData();
        mFoodTwo.setFoodId("2");
        mFoodTwo.setFoodName("Tea/Coffee");
        mFoodTwo.setFoodDescription("Tea/Coffee will be available till 12 PM");
        mFoodTwo.setFoodPrice(10.00);
        mFoodTwo.setFoodQuantity(2);
        mFoodTwo.setFoodImgURL("http://www.askdana.net/wp-content/uploads/2015/05/grandma-tea-cup.jpg");

        FoodData mFoodThree=new FoodData();
        mFoodThree.setFoodId("3");
        mFoodThree.setFoodName("Anna Sambar");
        mFoodThree.setFoodDescription("Anna Sambhar will be available from 12 PM onwards");
        mFoodThree.setFoodPrice(50.99);
        mFoodThree.setFoodQuantity(1);
        mFoodThree.setFoodImgURL("http://photos1.blogger.com/blogger/3627/3892/1600/DSC01462.2.jpg");

        FoodData mFoodFour=new FoodData();
        mFoodFour.setFoodId("4");
        mFoodFour.setFoodName("Curd Rice");
        mFoodFour.setFoodDescription("Curd Rice will be available from 12 PM onwards");
        mFoodFour.setFoodPrice(25.49);
        mFoodFour.setFoodQuantity(1);
        mFoodFour.setFoodImgURL("http://www.tourismandfood.com/wp-content/uploads/2016/03/South-Indian-Curd-rice.jpg");

        foodDataList.add(mFoodOne);
        foodDataList.add(mFoodTwo);
        foodDataList.add(mFoodThree);
        foodDataList.add(mFoodFour);
        return foodDataList;
    }
}
