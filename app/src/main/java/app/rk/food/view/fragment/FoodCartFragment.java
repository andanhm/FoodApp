package app.rk.food.view.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import app.rk.food.R;
import app.rk.food.model.FoodData;
import app.rk.food.utils.Log;
import app.rk.food.utils.Utility;
import app.rk.food.view.activity.MainActivity;
import app.rk.food.view.adapter.FoodCartAdapter;

/**
 *
 */
public class FoodCartFragment extends Fragment{
    private RecyclerView mRecyclerViewFood;
    private TextView textViewSubTotal,textViewDeliveryCharges,textViewTax,textViewCoupon,textViewTotal;
    private ImageView imageViewCouponEdit;
    private Button buttonCheckOut;
    private Activity mActivity;
    private ProgressDialog mProgressDialog;
    private Utility mUtility;
    private String TAG=getClass().getSimpleName();
    private List<FoodData> mFoodDataList;
    private FoodCartAdapter mFoodCartAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=getActivity();
        mUtility=new Utility(mActivity);
        mProgressDialog = new ProgressDialog(mActivity);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.progress_loading));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.client_cart_checkout_layout,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerViewFood = (RecyclerView) view.findViewById(R.id.recyclerViewFood);

        ImageView imageViewGiftCard=(ImageView)view.findViewById(R.id.imageViewGiftCard);
        imageViewGiftCard.setColorFilter(ContextCompat.getColor(mActivity,R.color.colorPrimary));

        textViewSubTotal=(TextView)view.findViewById(R.id.textViewSubTotal);
        textViewDeliveryCharges=(TextView)view.findViewById(R.id.textViewDeliveryCharges);
        textViewTax=(TextView)view.findViewById(R.id.textViewTax);
        textViewCoupon=(TextView)view.findViewById(R.id.textViewCoupon);
        textViewTotal=(TextView)view.findViewById(R.id.textViewTotal);

        updateBillUI();

        imageViewCouponEdit=(ImageView)view.findViewById(R.id.imageViewCouponEdit);
        imageViewCouponEdit.setOnClickListener(mListener);
        buttonCheckOut=(Button)view.findViewById(R.id.buttonCheckOut);
        buttonCheckOut.setOnClickListener(mListener);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewFood.setLayoutManager(layoutManager);
        updateCart();
    }
    private void updateCart(){
        mFoodCartAdapter = new FoodCartAdapter(mActivity, MainActivity.mCartItemList);
        mRecyclerViewFood.setAdapter(mFoodCartAdapter);
        mFoodCartAdapter.setOnDataChangeListener(new FoodCartAdapter.OnDataChangeListener(){
            public void onDataChanged(int size){
                updateBillUI();
            }
        });
    }

    private void updateBillUI(){
        mFoodDataList=MainActivity.mCartItemList;
        double subTotal=getSubTotal();
        double deliveryCharges=0.0;
        double tax=getTax(subTotal,14.5);
        double couponDiscount=20;
        double discount=getDiscount(subTotal,couponDiscount);
        double total=getTotal(subTotal,deliveryCharges,tax,discount);
        textViewSubTotal.setText(String.valueOf(subTotal));
        textViewDeliveryCharges.setText(String.valueOf(deliveryCharges));
        textViewTax.setText(String.valueOf(tax));
        textViewCoupon.setText(String.valueOf(discount));
        textViewTotal.setText(String.valueOf(total));
    }
    View.OnClickListener mListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imageViewCouponEdit:
                    mUtility.toast(R.string.working_on_it);
                    break;
                case R.id.buttonCheckOut:
                    mUtility.toast(R.string.working_on_it);
                    break;
            }
        }
    };

    private double getSubTotal(){
        double subTotal=0;
        for(FoodData mFoodData :  mFoodDataList){
            subTotal=subTotal+(mFoodData.getFoodPrice()*mFoodData.getFoodQuantity());
        }
        return Double.parseDouble(new DecimalFormat("#.##").format(subTotal));
    }

    private double getTotal(double subTotal,double deliveryCharges,double tax,double discount){
        double result=((subTotal+deliveryCharges+tax)-discount);
        return Double.parseDouble(new DecimalFormat("#.##").format(result));
    }

    private double getDiscount(double subTotal,double couponDiscount){
        double result=(couponDiscount/100)*subTotal;
        return Double.parseDouble(new DecimalFormat("#.##").format(result));
    }

    private double getTax(double subTotal,double vat){
        double result=(vat/100)*subTotal;
        return Double.parseDouble(new DecimalFormat("#.##").format(result));
    }
}
