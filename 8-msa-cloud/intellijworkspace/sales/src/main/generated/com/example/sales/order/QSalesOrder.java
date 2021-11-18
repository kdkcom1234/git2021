package com.example.sales.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSalesOrder is a Querydsl query type for SalesOrder
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSalesOrder extends EntityPathBase<SalesOrder> {

    private static final long serialVersionUID = -1662456811L;

    public static final QSalesOrder salesOrder = new QSalesOrder("salesOrder");

    public final StringPath address = createString("address");

    public final NumberPath<Long> createdTime = createNumber("createdTime", Long.class);

    public final StringPath date = createString("date");

    public final NumberPath<Integer> detailCnt = createNumber("detailCnt", Integer.class);

    public final ListPath<SalesOrderDetail, QSalesOrderDetail> details = this.<SalesOrderDetail, QSalesOrderDetail>createList("details", SalesOrderDetail.class, QSalesOrderDetail.class, PathInits.DIRECT2);

    public final StringPath firstProductName = createString("firstProductName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath status = createString("status");

    public final NumberPath<Long> totalAmount = createNumber("totalAmount", Long.class);

    public QSalesOrder(String variable) {
        super(SalesOrder.class, forVariable(variable));
    }

    public QSalesOrder(Path<? extends SalesOrder> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSalesOrder(PathMetadata metadata) {
        super(SalesOrder.class, metadata);
    }

}

