package com.example.sales.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSalesOrderDetail is a Querydsl query type for SalesOrderDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSalesOrderDetail extends EntityPathBase<SalesOrderDetail> {

    private static final long serialVersionUID = 1580846278L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSalesOrderDetail salesOrderDetail = new QSalesOrderDetail("salesOrderDetail");

    public final com.example.sales.product.QProduct product;

    public final StringPath productName = createString("productName");

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final NumberPath<Long> salesOrderId = createNumber("salesOrderId", Long.class);

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public final NumberPath<Integer> unitPrice = createNumber("unitPrice", Integer.class);

    public QSalesOrderDetail(String variable) {
        this(SalesOrderDetail.class, forVariable(variable), INITS);
    }

    public QSalesOrderDetail(Path<? extends SalesOrderDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSalesOrderDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSalesOrderDetail(PathMetadata metadata, PathInits inits) {
        this(SalesOrderDetail.class, metadata, inits);
    }

    public QSalesOrderDetail(Class<? extends SalesOrderDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new com.example.sales.product.QProduct(forProperty("product")) : null;
    }

}

