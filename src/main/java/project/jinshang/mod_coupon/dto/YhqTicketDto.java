package project.jinshang.mod_coupon.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author zzy
 * 用于优惠券礼包种 包含优惠券处理
 * @date 2018-05-26
 */
@Data
public class YhqTicketDto {

    @ApiModelProperty("编码")
    private String no;

    @ApiModelProperty("ticketid")
    private String ticketid;

    @ApiModelProperty("发放数量")
    private Long count;


}
