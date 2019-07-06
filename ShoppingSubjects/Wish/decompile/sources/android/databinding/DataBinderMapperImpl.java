package android.databinding;

import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.databinding.AddEditPaymentsAddressCellBinding;
import com.contextlogic.wish.databinding.AddEditPaymentsAddressFooterAddCellBinding;
import com.contextlogic.wish.databinding.AddEditPaymentsAddressHeaderCellBinding;
import com.contextlogic.wish.databinding.AddEditPaymentsFooterCellBinding;
import com.contextlogic.wish.databinding.AddEditPaymentsHeaderCellBinding;
import com.contextlogic.wish.databinding.ManagePaymentCellBinding;
import com.contextlogic.wish.databinding.ManagePaymentFooterCellBinding;
import com.contextlogic.wish.databinding.ManagePaymentHeaderCellBinding;

class DataBinderMapperImpl extends DataBinderMapper {
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        return null;
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        if (i != R.layout.add_edit_payments_header_cell) {
            switch (i) {
                case R.layout.add_edit_payments_address_cell /*2131427360*/:
                    Object tag = view.getTag();
                    if (tag == null) {
                        throw new RuntimeException("view must have a tag");
                    } else if ("layout/add_edit_payments_address_cell_0".equals(tag)) {
                        return new AddEditPaymentsAddressCellBinding(dataBindingComponent, view);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("The tag for add_edit_payments_address_cell is invalid. Received: ");
                        sb.append(tag);
                        throw new IllegalArgumentException(sb.toString());
                    }
                case R.layout.add_edit_payments_address_footer_add_cell /*2131427361*/:
                    Object tag2 = view.getTag();
                    if (tag2 == null) {
                        throw new RuntimeException("view must have a tag");
                    } else if ("layout/add_edit_payments_address_footer_add_cell_0".equals(tag2)) {
                        return new AddEditPaymentsAddressFooterAddCellBinding(dataBindingComponent, view);
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("The tag for add_edit_payments_address_footer_add_cell is invalid. Received: ");
                        sb2.append(tag2);
                        throw new IllegalArgumentException(sb2.toString());
                    }
                case R.layout.add_edit_payments_address_header_cell /*2131427362*/:
                    Object tag3 = view.getTag();
                    if (tag3 == null) {
                        throw new RuntimeException("view must have a tag");
                    } else if ("layout/add_edit_payments_address_header_cell_0".equals(tag3)) {
                        return new AddEditPaymentsAddressHeaderCellBinding(dataBindingComponent, view);
                    } else {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("The tag for add_edit_payments_address_header_cell is invalid. Received: ");
                        sb3.append(tag3);
                        throw new IllegalArgumentException(sb3.toString());
                    }
                case R.layout.add_edit_payments_footer_cell /*2131427363*/:
                    Object tag4 = view.getTag();
                    if (tag4 == null) {
                        throw new RuntimeException("view must have a tag");
                    } else if ("layout/add_edit_payments_footer_cell_0".equals(tag4)) {
                        return new AddEditPaymentsFooterCellBinding(dataBindingComponent, view);
                    } else {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("The tag for add_edit_payments_footer_cell is invalid. Received: ");
                        sb4.append(tag4);
                        throw new IllegalArgumentException(sb4.toString());
                    }
                default:
                    switch (i) {
                        case R.layout.manage_payment_cell /*2131427592*/:
                            Object tag5 = view.getTag();
                            if (tag5 == null) {
                                throw new RuntimeException("view must have a tag");
                            } else if ("layout/manage_payment_cell_0".equals(tag5)) {
                                return new ManagePaymentCellBinding(dataBindingComponent, view);
                            } else {
                                StringBuilder sb5 = new StringBuilder();
                                sb5.append("The tag for manage_payment_cell is invalid. Received: ");
                                sb5.append(tag5);
                                throw new IllegalArgumentException(sb5.toString());
                            }
                        case R.layout.manage_payment_footer_cell /*2131427593*/:
                            Object tag6 = view.getTag();
                            if (tag6 == null) {
                                throw new RuntimeException("view must have a tag");
                            } else if ("layout/manage_payment_footer_cell_0".equals(tag6)) {
                                return new ManagePaymentFooterCellBinding(dataBindingComponent, view);
                            } else {
                                StringBuilder sb6 = new StringBuilder();
                                sb6.append("The tag for manage_payment_footer_cell is invalid. Received: ");
                                sb6.append(tag6);
                                throw new IllegalArgumentException(sb6.toString());
                            }
                        case R.layout.manage_payment_header_cell /*2131427594*/:
                            Object tag7 = view.getTag();
                            if (tag7 == null) {
                                throw new RuntimeException("view must have a tag");
                            } else if ("layout/manage_payment_header_cell_0".equals(tag7)) {
                                return new ManagePaymentHeaderCellBinding(dataBindingComponent, view);
                            } else {
                                StringBuilder sb7 = new StringBuilder();
                                sb7.append("The tag for manage_payment_header_cell is invalid. Received: ");
                                sb7.append(tag7);
                                throw new IllegalArgumentException(sb7.toString());
                            }
                        default:
                            return null;
                    }
            }
        } else {
            Object tag8 = view.getTag();
            if (tag8 == null) {
                throw new RuntimeException("view must have a tag");
            } else if ("layout/add_edit_payments_header_cell_0".equals(tag8)) {
                return new AddEditPaymentsHeaderCellBinding(dataBindingComponent, view);
            } else {
                StringBuilder sb8 = new StringBuilder();
                sb8.append("The tag for add_edit_payments_header_cell is invalid. Received: ");
                sb8.append(tag8);
                throw new IllegalArgumentException(sb8.toString());
            }
        }
    }

    public int getLayoutId(String str) {
        if (str == null) {
            return 0;
        }
        switch (str.hashCode()) {
            case -1925059209:
                if (str.equals("layout/manage_payment_header_cell_0")) {
                    return R.layout.manage_payment_header_cell;
                }
                break;
            case -239033737:
                if (str.equals("layout/add_edit_payments_footer_cell_0")) {
                    return R.layout.add_edit_payments_footer_cell;
                }
                break;
            case -110569346:
                if (str.equals("layout/add_edit_payments_address_cell_0")) {
                    return R.layout.add_edit_payments_address_cell;
                }
                break;
            case 415064389:
                if (str.equals("layout/add_edit_payments_header_cell_0")) {
                    return R.layout.add_edit_payments_header_cell;
                }
                break;
            case 1394867786:
                if (str.equals("layout/add_edit_payments_address_footer_add_cell_0")) {
                    return R.layout.add_edit_payments_address_footer_add_cell;
                }
                break;
            case 1715809961:
                if (str.equals("layout/manage_payment_footer_cell_0")) {
                    return R.layout.manage_payment_footer_cell;
                }
                break;
            case 1921127482:
                if (str.equals("layout/add_edit_payments_address_header_cell_0")) {
                    return R.layout.add_edit_payments_address_header_cell;
                }
                break;
            case 1998469601:
                if (str.equals("layout/manage_payment_cell_0")) {
                    return R.layout.manage_payment_cell;
                }
                break;
        }
        return 0;
    }
}
