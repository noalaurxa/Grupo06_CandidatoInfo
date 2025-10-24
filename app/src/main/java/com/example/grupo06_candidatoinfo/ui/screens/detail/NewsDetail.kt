package com.example.grupo06_candidatoinfo.ui.screens.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler // <--- IMPORTANTE: Importación para abrir enlaces
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.grupo06_candidatoinfo.data.repository.MockDataRepository
import com.example.grupo06_candidatoinfo.model.NewsDetail
import coil.compose.AsyncImage

// Definición de Colores
private val mainPurple = Color(0xFF3C3472)
private val lightGrayBackground = Color(0xFFF0F0F5)
private val verifiedGreen = Color(0xFF4CAF50)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetail(
    navController: NavController,
    documentId: String?
) {
    val newsDetail: NewsDetail? = remember(documentId) {
        if (documentId != null) {
            MockDataRepository.getNewsDetail(documentId)
        } else {
            null
        }
    }

    var showImageDialog by remember { mutableStateOf(false) }

    // Obtenemos el UriHandler para abrir enlaces web
    val uriHandler = LocalUriHandler.current

    Scaffold(
        containerColor = lightGrayBackground,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Detalle de Información",
                        color = mainPurple,
                        fontWeight = FontWeight.Bold,
                        fontSize = 19.sp,
                        letterSpacing = (-0.5).sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = mainPurple,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            // TODO: Implementar funcionalidad de compartir
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Compartir",
                            tint = mainPurple,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        if (newsDetail == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Description,
                        contentDescription = null,
                        tint = Color.Red.copy(alpha = 0.3f),
                        modifier = Modifier.size(64.dp)
                    )
                    Text(
                        text = if (documentId.isNullOrEmpty())
                            "ID de noticia no proporcionado"
                        else
                            "Noticia no encontrada",
                        color = Color.Red,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                item {
                    NewsHeaderBlock(
                        title = newsDetail.title,
                        summary = newsDetail.summary,
                        imageUrl = newsDetail.imageUrl,
                        onImageClick = { showImageDialog = true }
                    )
                }

                item {
                    MetadataGrid(
                        date = newsDetail.date,
                        source = newsDetail.source,
                        relatedCandidateName = newsDetail.relatedCandidateName
                    )
                }

                item {
                    DescriptionCard(
                        description = newsDetail.fullDescription
                    )
                }

                // --- LLAMADA ACTUALIZADA ---
                item {
                    SourceButton(
                        source = newsDetail.source,
                        sourceUrl = newsDetail.sourceUrl, // Pasamos el URL
                        onNavigate = { url ->
                            // Lógica de navegación a la URL
                            try {
                                if (!url.isNullOrEmpty()) {
                                    uriHandler.openUri(url)
                                }
                            } catch (e: Exception) {
                                // Manejar el error si no se puede abrir el URL
                                println("Error al abrir URL: $e")
                            }
                        }
                    )
                }

                if (newsDetail.isVerified) {
                    item {
                        VerificationBanner()
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            // Diálogo para mostrar imagen en pantalla completa
            if (showImageDialog && newsDetail != null) {
                ImageFullScreenDialog(
                    imageUrl = newsDetail.imageUrl,
                    onDismiss = { showImageDialog = false }
                )
            }
        }
    }
}

// ... (NewsHeaderBlock, MetadataGrid, MetadataCard, DescriptionCard, VerificationBanner, ImageFullScreenDialog se mantienen iguales) ...

@Composable
fun NewsHeaderBlock(
    title: String,
    summary: String,
    imageUrl: String,
    onImageClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    onClick = onImageClick,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.15f),
                                Color.Transparent,
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.85f)
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(20.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(mainPurple)
                    .padding(horizontal = 18.dp, vertical = 7.dp)
            ) {
                Text(
                    text = "NOTICIA",
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(24.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    lineHeight = 30.sp,
                    letterSpacing = (-0.5).sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = summary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(alpha = 0.95f),
                    lineHeight = 22.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun MetadataGrid(date: String, source: String, relatedCandidateName: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MetadataCard(
                icon = Icons.Filled.CalendarToday,
                label = "FECHA",
                value = date,
                showIcon = false,
                modifier = Modifier.weight(1f)
            )

            MetadataCard(
                icon = Icons.Filled.Receipt,
                label = "FUENTE",
                value = source,
                showIcon = false,
                modifier = Modifier.weight(1f)
            )
        }

        MetadataCard(
            icon = Icons.Filled.Person,
            label = "RELACIONADO CON",
            value = relatedCandidateName,
            showIcon = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun MetadataCard(
    icon: ImageVector,
    label: String,
    value: String,
    showIcon: Boolean = false,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        if (showIcon) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(
                            brush = androidx.compose.ui.graphics.Brush.linearGradient(
                                colors = listOf(
                                    mainPurple.copy(alpha = 0.12f),
                                    mainPurple.copy(alpha = 0.08f)
                                )
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = mainPurple,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = label,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = mainPurple.copy(alpha = 0.7f),
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = value,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black.copy(alpha = 0.87f),
                        lineHeight = 20.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = label,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = mainPurple.copy(alpha = 0.7f),
                    letterSpacing = 0.5.sp
                )
                Text(
                    text = value,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black.copy(alpha = 0.87f),
                    lineHeight = 20.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun DescriptionCard(description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(20.dp)
                        .background(
                            mainPurple,
                            shape = RoundedCornerShape(2.dp)
                        )
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Descripción",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black.copy(alpha = 0.87f)
                )
            }

            Text(
                text = description,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black.copy(alpha = 0.7f),
                lineHeight = 24.sp
            )
        }
    }
}

@Composable
fun SourceButton(source: String, sourceUrl: String?, onNavigate: (String?) -> Unit) {
    val isClickable = !sourceUrl.isNullOrEmpty()

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isClickable) mainPurple else mainPurple.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = {
            if (isClickable) {
                onNavigate(sourceUrl)
            }
        },
        enabled = isClickable
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Ver noticia completa",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = source,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White.copy(alpha = 0.85f)
                )
            }

            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Ir a fuente",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .graphicsLayer(rotationZ = 180f)
            )
        }
    }
}

@Composable
fun VerificationBanner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = androidx.compose.ui.graphics.Brush.linearGradient(
                    colors = listOf(
                        verifiedGreen.copy(alpha = 0.12f),
                        verifiedGreen.copy(alpha = 0.08f)
                    )
                )
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.VerifiedUser,
            contentDescription = "Verificado",
            tint = verifiedGreen,
            modifier = Modifier.size(22.dp)
        )

        Text(
            text = "Información verificada por el JNE",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = verifiedGreen,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ImageFullScreenDialog(
    imageUrl: String,
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.95f))
            .clickable(
                onClick = onDismiss,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Imagen ampliada",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentScale = ContentScale.Fit
        )

        // Botón de cerrar
        IconButton(
            onClick = onDismiss,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White.copy(alpha = 0.2f))
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Cerrar",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .graphicsLayer(rotationZ = 90f)
            )
        }
    }
}